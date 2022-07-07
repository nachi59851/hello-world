package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.repository

import com.nachiket.dictionaryapplication.dictionary.core.util.Resource
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.local.WordInfoDao
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.model.WordInfo
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteInfoWords(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        }catch (e: HttpException){
            emit(Resource.Error(
                message = "Something went wrong.",
                data = wordInos
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check internet connection",
                data = wordInos
            ))
        }

        val newWordInfo = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(
            newWordInfo
        ))
    }
}