package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.local

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: WordInfoDao

}