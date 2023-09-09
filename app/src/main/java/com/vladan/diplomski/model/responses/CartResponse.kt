package com.vladan.diplomski.model.responses

import com.squareup.moshi.JsonClass
import com.vladan.diplomski.model.OrderElement

@JsonClass(generateAdapter = true)
data class CartResponse(
    val data: List<OrderElement>
)