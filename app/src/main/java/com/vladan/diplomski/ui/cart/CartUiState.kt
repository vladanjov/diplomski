package com.vladan.diplomski.ui.cart

import com.vladan.diplomski.model.OrderElement
import com.vladan.diplomski.model.Supplier
import com.vladan.diplomski.model.mockCartOrderElements

data class CartUiState(
    val cartOrders: List<OrderElement> = mockCartOrderElements
) {
    val groupedArticles = cartOrders.groupBy { it.article.supplier }

    fun getGroupedSum(supplier: Supplier): Int {
        var sum = 0
        groupedArticles[supplier]?.forEach {
            val numericString = it.article.price.replace(",00", "").replace(Regex("\\D"), "")
            numericString.toIntOrNull()?.let { int ->
                sum += (it.count * int)
            }

        }
        return sum
    }

    fun getSum(): Int {
        var sum = 0
        cartOrders.forEach {
            val numericString = it.article.price.replace(",00", "").replace(Regex("\\D"), "")
            numericString.toIntOrNull()?.let { int ->
                sum += (it.count * int)
            }

        }
        return sum
    }
}
