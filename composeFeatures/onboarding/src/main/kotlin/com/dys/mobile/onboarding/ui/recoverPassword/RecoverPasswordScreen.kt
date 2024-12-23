package com.dys.mobile.onboarding.ui.recoverPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent.CredentialChanged
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent.ReceiveSms
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordEvent.SendCode
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordSharedViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordState
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.outlinedtext.CredentialComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecoverPasswordScreen() {
    val viewModel = koinViewModel<RecoverPasswordSharedViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val recoverPasswordState = viewModel.recoverPasswordState.collectAsState().value

    RecoverPasswordContent(
        state = recoverPasswordState,
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
        val receiveSms: Boolean = state.receiveSms
        val messageRecoverCode: String
        val title: String
        val placeHolder: String
        val buttonReceiveBy: String
        val keyboardType: KeyboardType

        if (receiveSms) {
            messageRecoverCode = stringResource(R.string.text_recovery_code_phone_message)
            title = stringResource(R.string.text_cell_phone)
            placeHolder = stringResource(R.string.text_cell_phone_placeholder)
            buttonReceiveBy = stringResource(R.string.text_receiver_email)
            keyboardType = KeyboardType.Phone
        } else {
            messageRecoverCode = stringResource(R.string.text_recovery_code_message)
            title = stringResource(R.string.text_email)
            placeHolder = stringResource(R.string.text_email_placeholder)
            buttonReceiveBy = stringResource(R.string.text_receiver_sms)
            keyboardType = KeyboardType.Email
        }

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
                text = messageRecoverCode,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            CredentialComponent(
                modifier = Modifier.padding(horizontal = 24._dpw),
                title = title,
                placeHolder = placeHolder,
                keyboardType = keyboardType,
                maxLines = 1,
                value = state.credential,
                onValueChange = {
                    if (keyboardType == KeyboardType.Phone) {
                        if (it.length <= 11) event(CredentialChanged(it))
                    } else {
                        event(CredentialChanged(it))
                    }
                },
            )

            Spacer(modifier = Modifier.height(56._dph))

            FilledRoundButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60._dpw),
                text = stringResource(R.string.text_send_code),
                enabled = state.credential.isNotEmpty(),
                onClick = { event(SendCode) }
            )

            Spacer(modifier = Modifier.height(16._dph))

            TextComponent(
                text = stringResource(R.string.text_or),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(4._dph))

            TextButtonComponent(
                modifier = Modifier.wrapContentWidth(),
                text = buttonReceiveBy,
                style = MaterialTheme.typography.bodyMedium,
                horizontalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(0.dp),
                onClick = { event(ReceiveSms(!receiveSms)) }
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