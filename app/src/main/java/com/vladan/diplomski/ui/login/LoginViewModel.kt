package com.vladan.diplomski.ui.login

import android.util.Patterns
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
class LoginViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()

    fun typeEmail(value: String) {
        _state.update { it.copy(email = value, emailError = null) }
    }

    fun typePassword(value: String) {
        _state.update { it.copy(password = value, passwordError = null) }
    }

    private fun validate(email: String, password: String): Boolean {
        val errors = mutableListOf<Boolean>()

        if (password.isBlank()) {
            _state.update { it.copy(passwordError = "Šifra je obavezno polje.") }
            errors.add(false)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.update { it.copy(emailError = "E-mail nije validan.") }
            errors.add(false)
        }

        if (email.isBlank()) {
            _state.update { it.copy(emailError = "E-mail je obavezno polje.") }
            errors.add(false)
        }

        return errors.any { !it }
    }

    fun login(email: String, password: String) {
        if (validate(email, password)) {
            viewModelScope.launch {
                repository.login(email, password).let {
                    if (it is Result.Error) {
                        _events.send(
                            UiEvent.ToastEvent(
                                it.exception.message ?: "Došlo je do greške"
                            )
                        )
                    }
                }
            }
        }
    }

}