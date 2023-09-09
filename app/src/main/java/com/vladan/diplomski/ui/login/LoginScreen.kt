package com.vladan.diplomski.ui.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladan.diplomski.ui.common.InputWithError
import com.vladan.diplomski.ui.common.PasswordInputWithError
import com.vladan.diplomski.ui.theme.PrimaryColor
import com.vladan.diplomski.util.events.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(goToRegister: () -> Unit, viewModel: LoginViewModel) {

    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.events.collect {
            when (it) {
                is UiEvent.ToastEvent -> Toast.makeText(context, it.value, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    Scaffold(Modifier.fillMaxSize(), backgroundColor = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PRIJAVI SE",
                style = MaterialTheme.typography.caption.copy(
                    color = PrimaryColor,
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.size(60.dp))
            InputWithError(
                text = state.email,
                label = "E-mail",
                onTextChange = { viewModel.typeEmail(it) },
                modifier = Modifier.fillMaxWidth(),
                columnModifier = Modifier.fillMaxWidth(),
                onNextClick = { },
                errorMessage = state.emailError
            )
            Spacer(modifier = Modifier.size(20.dp))
            PasswordInputWithError(
                text = state.password,
                label = "Šifra",
                onTextChange = { viewModel.typePassword(it) },
                modifier = Modifier.fillMaxWidth(),
                onNextClick = { },
                errorMessage = state.passwordError
            )
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                modifier = Modifier
                    .height(54.dp)
                    .fillMaxWidth(),
                onClick = { viewModel.login(state.email, state.password) }) {
                Text(text = "Uloguj se")
            }
            Spacer(modifier = Modifier.size(30.dp))
            TextButton(
                modifier = Modifier
                    .height(54.dp)
                    .fillMaxWidth(),
                onClick = { goToRegister.invoke() }) {
                Text(text = "Registruj se")
            }
        }
    }
}