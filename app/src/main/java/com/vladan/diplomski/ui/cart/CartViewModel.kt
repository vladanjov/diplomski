package com.vladan.diplomski.ui.cart

import androidx.lifecycle.ViewModel
import com.vladan.diplomski.model.OrderElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState = _uiState.asStateFlow()

    fun editElement(item: OrderElement) {
        _uiState.update {
            it.copy(cartOrders = it.cartOrders.map {
                if (it.article == item.article) {
                    it.copy(count = item.count)
                } else it
            })
        }
    }

    fun removeElement(item: OrderElement) {
        _uiState.update { it.copy(cartOrders = it.cartOrders.filter { it != item }) }
    }

}