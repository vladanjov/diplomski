package com.vladan.diplomski.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.ui.theme.PrimaryColor

@Composable
fun TopBar(
    title: String
) {
    TopAppBar(
        backgroundColor = PrimaryColor,
        elevation = 0.dp,
        modifier = Modifier.height(78.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            Text(
                title,
                style = MaterialTheme.typography.caption.copy(
                    fontSize = 20.sp,
                    color = Color.White
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}