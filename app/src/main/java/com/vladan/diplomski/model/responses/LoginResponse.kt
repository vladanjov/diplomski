package com.vladan.diplomski.model.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val token: String,
    @Json(name = "refresh_token")
    val refreshToken: String
)
