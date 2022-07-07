package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(info: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity where word IN(:words)")
    suspend fun deleteInfoWords(words: List<String>)

     @Query("SELECT * FROM wordinfoentity where word LIKE '%' || :word || '%'")
     suspend fun getWordInfos(word: String): List<WordInfoEntity>
}