package com.vladan.diplomski.model.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    val name: String,
    val address: String,
    val pib: String,
    val email: String,
    val password: String
)
