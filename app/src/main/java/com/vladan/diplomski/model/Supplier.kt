package com.vladan.diplomski.model

data class Supplier(
    val id: String,
    val name: String,
    val address: String,
    val selected: Boolean
)

val mockSupplier1 = Supplier("1", "Air Point", "Adresa 1", false)
val mockSupplier2 = Supplier("2", "Agro Papuk", "Adresa 1", false)
val mockSupplier3 = Supplier("3", "DMS Pasta", "Adresa 1", true)
val mockSupplier4 = Supplier("4", "Destilerija Zaric", "Adresa 1", false)
val mockSupplier5 = Supplier("5", "Kuca Caja", "Adresa 1", true)
val mockSupplier6 = Supplier("6", "Proagro", "Adresa 1", false)

val mockSuppliers = listOf(
    mockSupplier1, mockSupplier2, mockSupplier3, mockSupplier4, mockSupplier5, mockSupplier6
)