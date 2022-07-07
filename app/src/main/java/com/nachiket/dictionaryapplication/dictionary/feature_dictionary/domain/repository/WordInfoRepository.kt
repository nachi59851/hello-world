package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.repository

import com.nachiket.dictionaryapplication.dictionary.core.util.Resource
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}