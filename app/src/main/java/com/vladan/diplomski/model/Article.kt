package com.vladan.diplomski.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: String,
    val name: String,
    val img: String?,
    val amount: String,
    val price: String,
    val supplier: Supplier
): Parcelable

val mockArticles = listOf(
    Article("1", "Coca cola / 2l", null, "paket (6 kom)", "1.000,00", mockSupplier1),
    Article("2", "Regent", null, "0.75l", "1.620,00", mockSupplier1),
    Article("3", "Finlandia cranbery, mango", null, "1l", "2.100,00", mockSupplier2),
    Article("4", "Atlantik Rubin", null, "paket 2l", "630,00",  mockSupplier2),
    Article("5", "Jagoda 42%", null, "0.05l / kom", "350,00", mockSupplier4),
    Article("6", "Borovnica 42%", null, "0.05l / kom", "450,00", mockSupplier4),
    Article("7", "Ribizla 42%", null, "0.05l / kom", "450,00", mockSupplier4),
    Article("8", "Naturera Ice Tea jagoda i borovnica 450g", null, "paket 2l", "1.120,00", mockSupplier5)
)