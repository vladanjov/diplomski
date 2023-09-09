package com.vladan.diplomski.ui.suppliers

import com.vladan.diplomski.model.Supplier
import com.vladan.diplomski.model.mockSuppliers

data class SuppliersUiState(
    val suppliers: List<Supplier> = mockSuppliers
)
