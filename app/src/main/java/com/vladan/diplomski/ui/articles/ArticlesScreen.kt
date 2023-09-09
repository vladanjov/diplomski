package com.vladan.diplomski.ui.articles

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.model.Article
import com.vladan.diplomski.ui.common.AddDialog
import com.vladan.diplomski.ui.common.TopBar
import com.vladan.diplomski.ui.theme.PrimaryColor
import com.vladan.diplomski.ui.theme.PrimaryVariantColor
import com.vladan.diplomski.ui.theme.SecondaryColor

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticlesScreen(viewModel: ArticlesViewModel) {

    val state = viewModel.state.collectAsState().value
    val addDialog = remember { mutableStateOf<Article?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(title = "Raspolozivi artikli") }) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 20.dp),
        ) {
            items(state.articles, key = { it.id }) {
                Article(article = it, onClickItem = { addDialog.value = it })
            }
        }

        addDialog.value?.let {
            AddDialog(
                article = it,
                title = "Da li želiš da dodaš ovaj artikal u korpu?",
                negativeButtonText = "Otkaži",
                positiveButtonText = "Dodaj",
                onClickNegativeButton = { addDialog.value = null },
                onClickPositiveButton = { },
                onDismissRequest = { addDialog.value = null })
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Article(article: Article, onClickItem: (Article) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .padding(0.dp),
        shape = RectangleShape,
        border = BorderStroke(0.1.dp, Color.LightGray),
        elevation = 1.dp,
        onClick = { onClickItem.invoke(article) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Text(
                text = article.name,
                style = MaterialTheme.typography.body1.copy(color = PrimaryColor, fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = article.supplier.name,
                style = MaterialTheme.typography.body1.copy(
                    color = PrimaryVariantColor,
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = article.amount,
                style = MaterialTheme.typography.body1.copy(color = PrimaryColor, fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = article.price + " RSD",
                style = MaterialTheme.typography.body1.copy(
                    color = SecondaryColor,
                    fontSize = 14.sp
                )
            )
        }
    }
}