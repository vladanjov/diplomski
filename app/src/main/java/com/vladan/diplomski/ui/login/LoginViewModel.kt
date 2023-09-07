package com.vladan.diplomski.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun typeEmail(value: String) {
        _state.update { it.copy(email = value, emailError = null) }
    }

    fun typePassword(value: String) {
        _state.update { it.copy(password = value, passwordError = null) }
    }

    fun validate(email: String, password: String): Boolean {
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
            Log.i("OVDE", "uspesno")
        }
    }

}