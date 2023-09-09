package com.vladan.diplomski.ui.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladan.diplomski.model.Article
import com.vladan.diplomski.repository.Repository
import com.vladan.diplomski.repository.network.Result
import com.vladan.diplomski.util.events.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesUiState())
    val state = _uiState.asStateFlow()

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()
    fun addArticle(article: Article) {
        viewModelScope.launch {
            repository.addArticleToCart(article).let { result ->
                if (result is Result.Error) {
                    _events.send(
                        UiEvent.ToastEvent(
                            result.exception.message ?: "Došlo je do greške"
                        )
                    )
                }
            }
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            repository.getArticles().let { result ->
                when (result) {
                    is Result.Error -> {
                        _events.send(
                            UiEvent.ToastEvent(
                                result.exception.message ?: "Došlo je do greške"
                            )
                        )
                    }

                    is Result.Success -> {
                        _uiState.update { it.copy(articles = result.data.data) }
                    }
                }
            }
        }
    }

    init {
        getArticles()
    }
}