package com.vladan.diplomski.model.responses

import com.squareup.moshi.JsonClass
import com.vladan.diplomski.model.Supplier

@JsonClass(generateAdapter = true)
data class SuppliersResponse(
    val data: List<Supplier>
)
