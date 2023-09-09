package com.vladan.diplomski.ui.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.model.OrderElement
import com.vladan.diplomski.model.Supplier
import com.vladan.diplomski.ui.common.EditDialog
import com.vladan.diplomski.ui.common.TopBar
import com.vladan.diplomski.ui.theme.PrimaryColor
import com.vladan.diplomski.ui.theme.SecondaryColor

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CartScreen(viewModel: CartViewModel) {

    val state = viewModel.uiState.collectAsState().value

    val editItemDialog = remember { mutableStateOf<OrderElement?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Button(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = { }) {
                Text(
                    text = "Poruči",
                    style = MaterialTheme.typography.button.copy(color = Color.White)
                )
            }
        },
        topBar = { TopBar(title = "Korpa") }) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 20.dp),
        ) {
            state.groupedArticles.forEach {
                item { SupplierHeader(supplier = it.key) }
                items(it.value) {
                    OrderElementViewHolder(orderElement = it) { editItemDialog.value = it }
                }
                item { SupplierSum(sum = state.getGroupedSum(it.key)) }
            }
            if (state.cartOrders.isNotEmpty()) {
                item {
                    Sum(sum = state.getSum())
                }
            }
        }

        editItemDialog.value?.let {
            EditDialog(
                item = it,
                onClickNegativeButton = { viewModel.removeElement(it) },
                onClickPositiveButton = { viewModel.editElement(it) },
                onDismissRequest = { editItemDialog.value = null })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OrderElementViewHolder(orderElement: OrderElement, onClickElement: (OrderElement) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        border = BorderStroke(0.1.dp, Color.LightGray),
        elevation = 1.dp,
        onClick = { onClickElement.invoke(orderElement) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = orderElement.article.name,
                    style = MaterialTheme.typography.body1.copy(
                        color = PrimaryColor,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "${orderElement.article.amount} / ${orderElement.article.price} RSD",
                    style = MaterialTheme.typography.body1.copy(
                        color = PrimaryColor,
                        fontSize = 12.sp
                    )
                )
            }
            Text(
                text = orderElement.count.toString(),
                style = MaterialTheme.typography.body1.copy(
                    color = SecondaryColor,
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.size(4.dp))

        }
    }
}

@Composable
fun SupplierHeader(supplier: Supplier) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .height(40.dp)
            .fillMaxWidth()
            .padding(0.dp),
        shape = RectangleShape,
        border = BorderStroke(0.1.dp, Color.LightGray),
        elevation = 1.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = supplier.name,
                style = MaterialTheme.typography.body1.copy(
                    color = PrimaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun SupplierSum(sum: Int) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(30.dp)
            .padding(0.dp),
        shape = RectangleShape,
        border = BorderStroke(0.1.dp, Color.LightGray),
        elevation = 1.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "= $sum,00 RSD",
                style = MaterialTheme.typography.body1.copy(
                    color = PrimaryColor,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
fun Sum(sum: Int) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(60.dp)
            .padding(0.dp),
        shape = RectangleShape,
        border = BorderStroke(0.1.dp, Color.LightGray),
        elevation = 1.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 12.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "UKUPNO: $sum,00 RSD",
                style = MaterialTheme.typography.body1.copy(
                    color = SecondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.End
            )
        }
    }
}