package com.vladan.diplomski.ui.suppliers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SuppliersViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _uiState = MutableStateFlow(SuppliersUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()

    fun changeSelectedStatus(id: String, value: Boolean) {
        viewModelScope.launch {
            repository.changeStatusOfSupplier(id, value).let { result ->
                when (result) {
                    is Result.Error -> _events.send(
                        UiEvent.ToastEvent(
                            result.exception.message ?: "Došlo je do greške"
                        )
                    )

                    is Result.Success -> {
                        _uiState.update { uiState ->
                            uiState.copy(suppliers = uiState.suppliers.map { supplier ->
                                if (supplier.id == id) {
                                    supplier.copy(selected = value)
                                } else supplier
                            })
                        }
                    }
                }
            }
        }
    }

    private fun getSuppliers(){
        viewModelScope.launch {
            repository.getSuppliers().let { result ->
                when (result) {
                    is Result.Error -> {
                        _events.send(
                            UiEvent.ToastEvent(
                                result.exception.message ?: "Došlo je do greške"
                            )
                        )
                    }

                    is Result.Success -> {
                        _uiState.update { it.copy(suppliers = result.data.data) }
                    }
                }
            }
        }
    }

    init {
        getSuppliers()
    }
}