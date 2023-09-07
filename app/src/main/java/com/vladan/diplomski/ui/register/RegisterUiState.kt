package com.vladan.diplomski.ui.register

data class RegisterUiState(
    val name: String = "",
    val pib: String = "",
    val email: String = "",
    val address: String = "",
    val password: String = "",
    val nameError: String? = null,
    val pibError: String? = null,
    val emailError: String? = null,
    val addressError: String? = null,
    val passwordError: String? = null
)
