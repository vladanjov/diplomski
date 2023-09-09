package com.vladan.diplomski.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

data class Order(
    val id: String,
    val supplier: Supplier,
    val articles: List<OrderElement>
)


@Parcelize
@JsonClass(generateAdapter = true)
data class OrderElement(
    val count: Int,
    val article: Article
) : Parcelable


val mockCartOrderElement1 = OrderElement(1, mockArticles[0])
val mockCartOrderElement2 = OrderElement(2, mockArticles[1])
val mockCartOrderElement3 = OrderElement(3, mockArticles[2])
val mockCartOrderElement4 = OrderElement(1, mockArticles[3])
val mockCartOrderElement5 = OrderElement(1, mockArticles[4])

val mockCartOrderElements = listOf(
    mockCartOrderElement1,
    mockCartOrderElement2,
    mockCartOrderElement3,
    mockCartOrderElement4
)
