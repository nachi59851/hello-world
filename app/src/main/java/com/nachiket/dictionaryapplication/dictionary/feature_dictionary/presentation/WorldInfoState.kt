package com.nachiket.dictionaryapplication.dictionary.feature_dictionary.presentation

import com.nachiket.dictionaryapplication.dictionary.core.util.Resource
import com.nachiket.dictionaryapplication.dictionary.feature_dictionary.domain.model.WordInfo

data class WorldInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)