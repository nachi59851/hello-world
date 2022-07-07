package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.use_cases.GetWordInfo
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetInfoUsedCase(repository: WordInfoRepository): GetWordInfo{
        return GetWordInfo(repository = repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(database: WordInfoDatabase, api: DictionaryApi): WordInfoRepository{
        return WordInfoRepositoryImpl(api = api,dao = database.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app:Application): WordInfoDatabase{
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        ).addTypeConverter(GsonParser(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun providesDictionaryApi(): DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}