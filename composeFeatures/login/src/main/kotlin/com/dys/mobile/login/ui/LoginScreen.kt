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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.viewmodels.login.LoginEvent
import com.dys.mobile.meucaminhao.viewmodels.login.LoginSharedViewModel
import com.dys.mobile.meucaminhao.viewmodels.login.LoginState
import com.dys.mobile.uikit.R
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.outlinedtext.CredentialComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen() {
    val viewModel = koinViewModel<LoginSharedViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val loginState = viewModel.loginState.collectAsState().value

    LoginContent(
        loginState,
        event = viewModel::onEvent
    )

    if (uiState is UiState.Loading && uiState.isLoading) {
        // TODO: Show loading
    }

    if (uiState is UiState.ErrorState) {
        // TODO: Show error
    }
}

@Composable
fun LoginContent(
    loginState: LoginState,
    event: (LoginEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(24._dph),
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

            Spacer(modifier = Modifier.height(60._dph))

            Credentials(loginState, event)

            LoginOptions(event)
        }
    }
}

@Composable
private fun Credentials(
    loginState: LoginState,
    event: (LoginEvent) -> Unit
) {
    TextComponent(
        text = stringResource(R.string.text_access_your_app),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge
    )

    Spacer(modifier = Modifier.height(16._dph))

    CredentialComponent(
        title = stringResource(R.string.text_email),
        placeHolder = stringResource(R.string.text_email_placeholder),
        value = loginState.email,
        onValueChange = { event(LoginEvent.EmailChanged(it)) },
    )

    Spacer(modifier = Modifier.height(16._dph))

    CredentialComponent(
        title = stringResource(R.string.text_password),
        placeHolder = stringResource(R.string.text_password_placeholder),
        isPassword = true,
        value = loginState.password,
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

    Spacer(modifier = Modifier.height(56._dph))

    FilledRoundButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60._dpw),
        text = stringResource(R.string.text_access),
        onClick = { event(LoginEvent.Access) }
    )

    Spacer(modifier = Modifier.height(16._dph))

    TextComponent(
        text = stringResource(R.string.text_or),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(16._dph))

    OutlinedRoundButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60._dpw),
        text = stringResource(R.string.text_access_with_google),
        icon = R.drawable.ic_google,
        contentPadding = PaddingValues(0.dp),
        onClick = { event(LoginEvent.AccessWithGoogle) }
    )

    Spacer(modifier = Modifier.height(16._dph))

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

@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun LoginScreenPreview() {
    MeuCaminhaoTheme {
        LoginContent(
            loginState = LoginState(),
            event = {}
        )
    }
}