package com.dys.mobile.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.CredentialComponent
import com.dys.mobile.uikit.components.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.TextButtonComponent
import com.dys.mobile.uikit.components.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun LoginScreen() {
    val viewModel = viewModel<LoginViewModel>()
    val state = viewModel.uiStateFlow.collectAsState().value

    LoginContent(
        state = state,
        event = viewModel::onEvent
    )
}

@Composable
fun LoginContent(
    state: LoginState,
    event: (LoginEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextComponent(
                text = stringResource(R.string.text_control_your_trips),
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            TextComponent(
                text = stringResource(R.string.text_start_here),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(60.dp))

            Credentials(state, event)

            LoginOptions(event)
        }
    }
}

@Composable
private fun Credentials(
    state: LoginState,
    event: (LoginEvent) -> Unit
) {
    TextComponent(
        text = stringResource(R.string.text_access_your_app),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge
    )

    Spacer(modifier = Modifier.height(16.dp))

    CredentialComponent(
        title = stringResource(R.string.text_email),
        placeHolder = stringResource(R.string.text_email_placeholder),
        value = state.email,
        onValueChange = { event(LoginEvent.EmailChanged(it)) },
    )

    Spacer(modifier = Modifier.height(16.dp))

    CredentialComponent(
        title = stringResource(R.string.text_password),
        placeHolder = stringResource(R.string.text_password_placeholder),
        isPassword = true,
        value = state.password,
        onValueChange = { event(LoginEvent.PasswordChanged(it)) },
    )
}

@Composable
private fun LoginOptions(event: (LoginEvent) -> Unit) {
    TextButtonComponent(
        text = stringResource(R.string.text_forgot_my_password),
        style = MaterialTheme.typography.bodyMedium,
        contentPadding = PaddingValues(0.dp),
        onClick = { event(LoginEvent.ForgotPassword) }
    )

    Spacer(modifier = Modifier.height(56.dp))

    FilledRoundButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        text = stringResource(R.string.text_access),
        onClick = { event(LoginEvent.Access) }
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = stringResource(R.string.text_or),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedRoundButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        text = stringResource(R.string.text_access_with_google),
        icon = R.drawable.ic_google,
        contentPadding = PaddingValues(0.dp),
        onClick = {event(LoginEvent.AccessWithGoogle) }
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = stringResource(R.string.text_dont_have_account),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )

    TextButtonComponent(
        text = stringResource(R.string.text_register),
        style = MaterialTheme.typography.bodyMedium,
        horizontalArrangement = Arrangement.Center,
        onClick = { event(LoginEvent.Register) }
    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    MeuCaminhaoTheme {
        LoginContent(
            state = LoginState(),
            event = {}
        )
    }
}