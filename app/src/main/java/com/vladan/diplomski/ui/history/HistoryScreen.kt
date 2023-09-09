package com.vladan.diplomski.ui.history

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vladan.diplomski.ui.cart.OrderElementViewHolder
import com.vladan.diplomski.ui.cart.SupplierHeader
import com.vladan.diplomski.ui.cart.SupplierSum
import com.vladan.diplomski.ui.common.TopBar
import com.vladan.diplomski.util.events.UiEvent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {

    val state = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.events.collect {
            when (it) {
                is UiEvent.ToastEvent -> Toast.makeText(context, it.value, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(title = "Istorija kupovine") }) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 20.dp),
        ) {
            state.orders.forEach {
                item { SupplierHeader(supplier = it.supplier) }
                items(it.articles) {
                    OrderElementViewHolder(orderElement = it) { }
                }
                item { SupplierSum(sum = it.getSum()) }
            }
        }
    }
}