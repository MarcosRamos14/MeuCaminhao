package com.dys.mobile.onboarding.ui.recoverPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent.EmailChanged
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent.SendCode
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordSharedViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordState
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.outlinedtext.CredentialComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecoverPasswordScreen(navController: NavController) {
    val viewModel = koinViewModel<RecoverPasswordSharedViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val recoverPasswordState = viewModel.recoverPasswordState.collectAsState().value

    RecoverPasswordContent(
        state = recoverPasswordState,
        event = viewModel::onEvent
    )

    LaunchedEffect(uiState) {
        when(uiState) {
            is UiState.Loading -> {
                // TODO: Show loading
            }
            is UiState.ErrorState -> {
                // TODO: Show error
            }
            is UiState.Navigation<*> -> {
                uiState.content.getContentIfNotHandled()?.let { route ->
                    navController.navigate(route.toString())
                }
            }
        }
    }

    if (uiState is UiState.Loading && uiState.isLoading) {
        // TODO: Show loading
    }

    if (uiState is UiState.ErrorState) {
        // TODO: Show error
    }
}

@Composable
private fun RecoverPasswordContent(
    state: RecoverPasswordState,
    event: (RecoverPasswordEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.text_recover_password)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.thumbnail_forget_password),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(16._dph))

            TextComponent(
                text = stringResource(R.string.text_forgot_password_question),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(R.string.text_recovery_code_message),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            CredentialComponent(
                modifier = Modifier.padding(horizontal = 24._dpw),
                title = stringResource(R.string.text_email),
                placeHolder = stringResource(R.string.text_email_placeholder),
                maxLines = 1,
                value = state.email,
                onValueChange = { event(EmailChanged(it)) },
            )

            Spacer(modifier = Modifier.height(56._dph))

            FilledRoundButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60._dpw),
                text = stringResource(R.string.text_send_code),
                enabled = state.email.isNotEmpty(),
                onClick = { event(SendCode) }
            )
        }
    }
}

@Preview
@Composable
private fun RecoverPasswordPreview() {
    MeuCaminhaoTheme {
        RecoverPasswordContent(
            state = RecoverPasswordState(),
            event = {}
        )
    }
}