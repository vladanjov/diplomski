package com.vladan.diplomski.ui.suppliers

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SuppliersViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SuppliersUiState())
    val state = _state.asStateFlow()

    fun changeSelectedStatus(id: String, value: Boolean) {
        _state.update { uiState ->
            uiState.copy(suppliers = uiState.suppliers.map { supplier ->
                if (supplier.id == id) {
                    supplier.copy(selected = value)
                } else supplier
            })
        }
    }
}