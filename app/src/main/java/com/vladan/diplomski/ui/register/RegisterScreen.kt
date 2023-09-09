package com.vladan.diplomski.ui.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
fun RegisterScreen(viewModel: RegisterViewModel) {

    val state = viewModel.state.collectAsState().value
    val scrollState = rememberScrollState()
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
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(60.dp))
            Text(
                text = "Registracija",
                style = MaterialTheme.typography.caption.copy(
                    color = PrimaryColor,
                    fontSize = 24.sp
                )
            )
            Spacer(modifier = Modifier.size(40.dp))
            InputWithError(
                text = state.name,
                label = "Ime kompanije",
                onTextChange = { viewModel.typeName(it) },
                modifier = Modifier.fillMaxWidth(),
                columnModifier = Modifier.fillMaxWidth(),
                onNextClick = { },
                errorMessage = state.nameError
            )
            Spacer(modifier = Modifier.size(20.dp))
            InputWithError(
                text = state.pib,
                label = "PIB",
                onTextChange = { viewModel.typePib(it) },
                modifier = Modifier.fillMaxWidth(),
                columnModifier = Modifier.fillMaxWidth(),
                onNextClick = { },
                errorMessage = state.pibError
            )
            Spacer(modifier = Modifier.size(20.dp))
            InputWithError(
                text = state.address,
                label = "Adresa",
                onTextChange = { viewModel.typeAddress(it) },
                modifier = Modifier.fillMaxWidth(),
                columnModifier = Modifier.fillMaxWidth(),
                onNextClick = { },
                errorMessage = state.addressError
            )
            Spacer(modifier = Modifier.size(20.dp))
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
            Spacer(modifier = Modifier.size(40.dp))
            Button(
                modifier = Modifier
                    .height(54.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.register(
                        state.name,
                        state.email,
                        state.pib,
                        state.address,
                        state.password
                    )
                }) {
                Text(text = "Registruj se")
            }
        }
    }

}