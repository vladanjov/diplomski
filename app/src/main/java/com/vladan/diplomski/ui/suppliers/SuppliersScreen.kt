package com.vladan.diplomski.ui.suppliers

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.model.Supplier
import com.vladan.diplomski.ui.common.TopBar
import com.vladan.diplomski.ui.theme.PrimaryColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuppliersScreen(viewModel: SuppliersViewModel) {

    val state = viewModel.state.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(title = "Dodati dobavljači") }) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 20.dp),
        ) {
            items(state.suppliers, key = { it.id }) { supplier ->
                SupplierViewHolder(
                    supplier = supplier,
                    onClickButton = { viewModel.changeSelectedStatus(supplier.id, it) })
            }
        }
    }
}

@Composable
fun SupplierViewHolder(supplier: Supplier, onClickButton: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .padding(1.dp),
        shape = RectangleShape,
        elevation = 1.dp,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = supplier.name,
                    style = MaterialTheme.typography.body1.copy(
                        color = PrimaryColor,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = supplier.address,
                    style = MaterialTheme.typography.body1.copy(
                        color = PrimaryColor,
                        fontSize = 12.sp
                    )
                )
            }
            Checkbox(
                modifier = Modifier.padding(12.dp),
                checked = supplier.selected,
                onCheckedChange = {
                    Log.i("OVDE", it.toString())
                    onClickButton.invoke(it)
                })
        }
    }
}
