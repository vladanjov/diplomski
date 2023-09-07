package com.vladan.diplomski.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    fun typeName(value: String) {
        _state.update { it.copy(name = value, nameError = null) }
    }

    fun typePib(value: String) {
        _state.update { it.copy(pib = value, pibError = null) }
    }

    fun typeEmail(value: String) {
        _state.update { it.copy(email = value, emailError = null) }
    }

    fun typeAddress(value: String) {
        _state.update { it.copy(address = value, addressError = null) }
    }

    fun typePassword(value: String) {
        _state.update { it.copy(password = value, passwordError = null) }
    }

    fun validate(
        name: String,
        email: String,
        pib: String,
        address: String,
        password: String
    ): Boolean {
        val errors = mutableListOf<Boolean>()

        if (name.isBlank()) {
            _state.update { it.copy(nameError = "Ime je obavezno polje.") }
            errors.add(false)
        }

        if (email.isBlank()) {
            _state.update { it.copy(emailError = "E-mail je obavezno polje.") }
            errors.add(false)
        }

        if (pib.isBlank()) {
            _state.update { it.copy(pibError = "PIB je obavezno polje.") }
            errors.add(false)
        }

        if (address.isBlank()) {
            _state.update { it.copy(addressError = "Adresa je obavezno polje.") }
            errors.add(false)
        }

        if (password.isBlank()) {
            _state.update { it.copy(passwordError = "Šifra je obavezno polje.") }
            errors.add(false)
        }

        return errors.any { !it }
    }

    fun register(name: String, email: String, pib: String, address: String, password: String) {
        if (validate(name, email, pib, address, password)) {
            Log.i("OVDE", "Register")
        }
    }
}