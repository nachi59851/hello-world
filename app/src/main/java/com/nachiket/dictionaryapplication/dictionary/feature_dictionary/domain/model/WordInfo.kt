package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.model

import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.data.remote.dto.LicenseDto

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)
