package com.vladan.diplomski.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.R
import com.vladan.diplomski.model.Article
import com.vladan.diplomski.model.OrderElement
import com.vladan.diplomski.ui.theme.SecondaryColor

@ExperimentalComposeUiApi
@Composable
fun AddDialog(
    title: String,
    article: Article,
    negativeButtonText: String,
    positiveButtonText: String,
    onClickNegativeButton: () -> Unit,
    onClickPositiveButton: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismissRequest() },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = article.name,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${article.amount} / (${article.price} RSD)",

                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
            }
        },
        buttons = {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButtonBase(
                    onClick = { onClickNegativeButton() },
                    text = negativeButtonText,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                )
                TextButtonBase(
                    onClick = { onClickPositiveButton() },
                    text = positiveButtonText,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    style = MaterialTheme.typography.body1.copy(
                        color = SecondaryColor,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    )
}


@ExperimentalComposeUiApi
@Composable
fun EditDialog(
    item: OrderElement,
    onClickNegativeButton: (OrderElement) -> Unit,
    onClickPositiveButton: (OrderElement) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val localItem = rememberSaveable { mutableStateOf(item) }

    AlertDialog(onDismissRequest = { onDismissRequest() },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = item.article.name,
                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        localItem.value = localItem.value.copy(count = localItem.value.count - 1)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "",
                            tint = SecondaryColor
                        )
                    }
                    Text(
                        text = localItem.value.count.toString(),
                        style = MaterialTheme.typography.caption.copy(
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    IconButton(onClick = {
                        localItem.value = localItem.value.copy(count = localItem.value.count + 1)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "",
                            tint = SecondaryColor
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${item.article.amount} / (${item.article.price} RSD)",

                    style = MaterialTheme.typography.caption.copy(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )
            }
        },
        buttons = {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButtonBase(
                    onClick = { onClickNegativeButton(localItem.value).also { onDismissRequest.invoke() } },
                    text = "Izbaci iz korpe",
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Medium
                    )
                )
                TextButtonBase(
                    onClick = { onClickPositiveButton(localItem.value).also { onDismissRequest.invoke() } },
                    text = "Sačuvaj izmene",
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    style = MaterialTheme.typography.body1.copy(
                        color = SecondaryColor,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    )
}