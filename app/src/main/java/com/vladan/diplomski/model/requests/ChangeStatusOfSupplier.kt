package com.vladan.diplomski.model.requests

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChangeStatusOfSupplier(
    @Json(name = "supplier_id")
    val supplierId: String,
    val status: Boolean
)

