package com.dys.mobile.onboarding.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.PasswordTooShort
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.PasswordsDoNotMatch
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.isAllFieldsFilled
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.outlinedtext.CredentialComponent
import com.dys.mobile.uikit.components.stepper.StepperComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateAccountScreen(navController: NavController) {
    val viewModel = koinViewModel<RegisterViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val registerState = viewModel.registerState.collectAsState().value

    CreateAccountScreenContent(
        state = registerState,
        event = viewModel::onCreateAccountEvent
    )

    LaunchedEffect(uiState) {
        if (uiState is UiState.Navigation<*>) {
            uiState.content.getContentIfNotHandled()?.let { route ->
                navController.navigate(route.toString())
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            if (navController.currentBackStackEntry?.destination?.route
                    .equals(Routes.LoginScreen.route)
            ) {
                viewModel.resetState()
            }
        }
    }
}

@Composable
fun CreateAccountScreenContent(
    state: RegisterState = RegisterState(),
    event: (RegisterEvent) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.text_register)
            )
        },
        bottomBar = {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black.copy(alpha = 0.15f))
                )
                BottomAppBar(
                    modifier = Modifier.shadow(6.dp),
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    FilledRoundButtonComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 60.dp),
                        text = stringResource(R.string.common_text_advance),
                        enabled = state.isAllFieldsFilled(),
                        onClick = { event(RegisterEvent.Advance) }
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.onBackground
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
            Spacer(modifier = Modifier.height(40._dph))

            StepperComponent(
                currentStep = 1,
                textSteps = listOf(
                    stringResource(R.string.text_step_create_account),
                    stringResource(R.string.text_step_account_type),
                    stringResource(R.string.text_step_start_using)
                )
            )

            Spacer(modifier = Modifier.height(40._dph))

            TextComponent(
                text = stringResource(R.string.text_create_account),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(R.string.text_register_welcome_message),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            CredentialComponent(
                title = stringResource(R.string.text_full_name),
                placeHolder = stringResource(R.string.common_text_enter),
                value = state.fullName,
                onValueChange = { event(RegisterEvent.FullNameChanged(it)) },
            )

            Spacer(modifier = Modifier.height(16._dph))

            CredentialComponent(
                title = stringResource(R.string.text_email),
                titleColor = if (state.emailError != null) Red60 else null,
                placeHolder = stringResource(R.string.text_email_placeholder),
                isError = state.emailError != null,
                errorMessage = if (state.emailError != null) R.string.text_invalid_email else null,
                value = state.email,
                onValueChange = { event(RegisterEvent.EmailChanged(it)) },
            )

            Spacer(modifier = Modifier.height(16._dph))

            CredentialComponent(
                title = stringResource(R.string.text_create_password),
                titleColor = if (state.passwordError != null) Red60 else null,
                placeHolder = stringResource(R.string.text_password_placeholder),
                isPassword = true,
                isError = state.passwordError != null,
                value = state.password,
                onValueChange = { event(RegisterEvent.PasswordChanged(it)) },
            )

            Spacer(modifier = Modifier.height(16._dph))

            val passwordErrorMessage = when (state.passwordError) {
                is PasswordTooShort -> R.string.text_password_too_short
                is PasswordsDoNotMatch -> R.string.text_passwords_do_not_match
                else -> R.string.common_text_generic_error
            }

            CredentialComponent(
                title = stringResource(R.string.text_confirm_password),
                titleColor = if (state.passwordError != null) Red60 else null,
                placeHolder = stringResource(R.string.text_password_placeholder),
                isPassword = true,
                isError = state.passwordError != null,
                errorMessage = if (state.passwordError != null) passwordErrorMessage else null,
                value = state.confirmPassword,
                onValueChange = { event(RegisterEvent.ConfirmPasswordChanged(it)) },
            )

            //TODO: Access terms

            Spacer(modifier = Modifier.height(32._dph))
        }
    }
}

@Preview
@Composable
private fun CreateAccountScreenPreview() {
    MeuCaminhaoTheme {
        CreateAccountScreenContent()
    }
}