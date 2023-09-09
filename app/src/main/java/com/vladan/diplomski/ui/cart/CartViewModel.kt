package com.vladan.diplomski.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladan.diplomski.model.OrderElement
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
class CartViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()

    fun editElement(item: OrderElement) {
        viewModelScope.launch {
            repository.editCartElement(item).let { result ->
                when (result) {
                    is Result.Error -> {
                        _events.send(
                            UiEvent.ToastEvent(
                                result.exception.message ?: "Došlo je do greške"
                            )
                        )
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(cartOrders = it.cartOrders.map {
                                if (it.article == item.article) {
                                    it.copy(count = item.count)
                                } else it
                            })
                        }
                    }
                }
            }
        }
    }

    fun removeElement(item: OrderElement) {
        viewModelScope.launch {
            repository.deleteArticleFromCart(item.article).let { result ->
                when (result) {
                    is Result.Error -> {
                        _events.send(
                            UiEvent.ToastEvent(
                                result.exception.message ?: "Došlo je do greške"
                            )
                        )
                    }

                    is Result.Success -> {
                        _uiState.update { it.copy(cartOrders = it.cartOrders.filter { it != item }) }
                    }
                }
            }
        }
    }

    private fun getCart() {
        viewModelScope.launch {
            repository.getCart().let { result ->
                when (result) {
                    is Result.Error -> {
                        _events.send(
                            UiEvent.ToastEvent(
                                result.exception.message ?: "Došlo je do greške"
                            )
                        )
                    }

                    is Result.Success -> _uiState.update { it.copy(cartOrders = result.data.data) }
                }
            }
        }
    }

    init {
        getCart()
    }

}