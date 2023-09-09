package com.vladan.diplomski.ui.history

import com.vladan.diplomski.model.Order
import com.vladan.diplomski.model.mockOrders

data class HistoryUiState(
    val orders: List<Order> = mockOrders
)
