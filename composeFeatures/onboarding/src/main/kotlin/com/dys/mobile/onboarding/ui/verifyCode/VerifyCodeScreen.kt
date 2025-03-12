package com.dys.mobile.onboarding.ui.verifyCode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode.VerifyCodeEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode.VerifyCodeState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode.VerifyCodeViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.outlinedtext.OtpInputFieldComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerifyCodeScreen(navController: NavController) {
    val viewModel = koinViewModel<VerifyCodeViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val verifyCodeState = viewModel.verifyCodeState.collectAsState().value

    VerifyCodeContent(
        state = verifyCodeState,
        event = viewModel::onEvent
    )

    LaunchedEffect(uiState) {
        when(uiState) {
            is  UiState.Loading -> {
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
}

@Composable
fun VerifyCodeContent(
    state: VerifyCodeState,
    event: (VerifyCodeEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.text_recover_password),
                hasDivider = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24._dpw)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.thumbnail_password),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(32._dph))

            TextComponent(
                text = stringResource(R.string.text_enter_verification_code),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(R.string.text_enter_six_digit_code),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8._dpw, Alignment.CenterHorizontally)
            ) {
                val focusRequesters = remember {
                    List(6) { FocusRequester() }
                }

                state.code.forEachIndexed { index, number ->
                    val nextFocusRequester = if (index < 5) focusRequesters[index + 1] else null
                    OtpInputFieldComponent(
                        number = number,
                        focusRequester = focusRequesters[index],
                        nextFocusRequester = nextFocusRequester,
                        onFocusChanged = { isFocused ->
                            if (isFocused) event(VerifyCodeEvent.ChangeFieldFocused(index))
                        },
                        onNumberChanged = { newNumber ->
                            event(VerifyCodeEvent.NumberChanged(newNumber, index))
                        },
                        onKeyboardBack = {
                            if (index > 0) focusRequesters[index - 1].requestFocus()
                        },
                        isError = state.codeError != null
                    )
                }
            }

            if (state.codeError != null) {
                Spacer(modifier = Modifier.height(8._dph))

                TextComponent(
                    text = stringResource(R.string.text_invalid_expired_code),
                    color = Red60,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(40._dph))

            FilledRoundButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60._dpw),

                text = stringResource(R.string.text_recover_password),
                enabled = state.code.all { it != null },
                onClick = { event(VerifyCodeEvent.VerifyCode) }
            )

            Spacer(modifier = Modifier.height(24._dph))

            TextButtonComponent(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.text_no_code_received),
                style = MaterialTheme.typography.bodyMedium,
                horizontalArrangement = Arrangement.Center,
                onClick = { event(VerifyCodeEvent.NoCodeReceived) }
            )
        }
    }
}

@Preview
@Composable
private fun VerifyCodePreview() {
    MeuCaminhaoTheme {
        VerifyCodeContent(
            state = VerifyCodeState(),
            event = {}
        )
    }
}