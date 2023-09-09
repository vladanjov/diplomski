package com.vladan.diplomski.model.requests

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vladan.diplomski.model.OrderElement

@JsonClass(generateAdapter = true)
data class UpdateOrderElementRequest(
    @Json(name = "order_element")
    val orderElement: OrderElement
)
