package com.vladan.diplomski.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.R
import com.vladan.diplomski.ui.theme.ErrorColor
import com.vladan.diplomski.ui.theme.SecondaryColor

@ExperimentalComposeUiApi
@Composable
fun InputWithError(
    text: String?,
    label: String,
    onTextChange: (String) -> Unit,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    ),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier,
    columnModifier: Modifier,
    onNextClick: () -> Unit,
    enabled: Boolean = true,
    maxLines: Int = 1
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = columnModifier) {
        OutlinedTextField(
            value = text ?: "",
            label = { Text(label, style = MaterialTheme.typography.subtitle2) },
            onValueChange = onTextChange,
            modifier = modifier,
            singleLine = maxLines == 1,
            maxLines = maxLines,
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            ),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = { onNextClick() },
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ), colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errorMessage == null) MaterialTheme.colors.primary else ErrorColor,
                unfocusedBorderColor = if (errorMessage == null) MaterialTheme.colors.onSurface else ErrorColor
            ), enabled = enabled
        )
        errorMessage?.let { text -> if (text.isNotEmpty()) TextError(message = errorMessage) }
    }
}


@ExperimentalComposeUiApi
@Composable
fun PasswordInputWithError(
    text: String?,
    label: String,
    onTextChange: (String) -> Unit,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    modifier: Modifier,
    onNextClick: () -> Unit
) {
    var hasVisualTransformation by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text ?: "",
            label = { Text(label, style = MaterialTheme.typography.subtitle2) },
            onValueChange = onTextChange,
            modifier = modifier,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = { onNextClick() },
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            trailingIcon = {
                IconButton(
                    onClick = { hasVisualTransformation = !hasVisualTransformation },
                    modifier = Modifier.padding(4.dp),
                ) {
                    if (hasVisualTransformation)
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                            contentDescription = "",
                            tint = MaterialTheme.colors.primary
                        )
                    else
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                            contentDescription = "",
                            tint = MaterialTheme.colors.primaryVariant
                        )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errorMessage == null) MaterialTheme.colors.primary else ErrorColor,
                unfocusedBorderColor = if (errorMessage == null) MaterialTheme.colors.onSurface else ErrorColor
            ),
            visualTransformation = if (hasVisualTransformation) PasswordVisualTransformation() else VisualTransformation.None
        )
        errorMessage?.let { text -> if (text.isNotEmpty()) TextError(message = errorMessage) }
    }
}


@Composable
fun TextError(message: String) {
    Text(
        text = message,
        modifier = Modifier.padding(start = 2.dp, top = 2.dp),
        color = ErrorColor
    )
}

@ExperimentalComposeUiApi
@Composable
fun TextButtonBase(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    style: TextStyle? = null,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    TextButton(
        onClick = {
            onClick().also {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        },
        modifier = modifier
    ) {
        Text(
            text,
            style = style ?: MaterialTheme.typography.body1.copy(color = SecondaryColor)
        )
    }
}