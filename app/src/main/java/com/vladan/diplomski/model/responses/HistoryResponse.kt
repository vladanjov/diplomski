package com.vladan.diplomski.model.responses

import com.squareup.moshi.JsonClass
import com.vladan.diplomski.model.Order

@JsonClass(generateAdapter = true)
class HistoryResponse(
    val data: List<Order>
)