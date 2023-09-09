package com.vladan.diplomski.ui.articles

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesUiState())
    val state = _uiState.asStateFlow()
}