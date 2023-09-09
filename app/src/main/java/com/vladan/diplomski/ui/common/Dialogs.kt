package com.vladan.diplomski.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.model.Article
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