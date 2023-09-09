package com.vladan.diplomski.model.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DefaultResponse(
    val message: String
)
