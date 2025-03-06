package com.dys.mobile.onboarding.ui.newPassword

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.PasswordTooShort
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.PasswordsDoNotMatch
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword.NewPasswordEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword.NewPasswordState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword.NewPasswordViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.bottomSheet.CommonBottomSheet
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.outlinedtext.CredentialComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewPasswordScreen(navController: NavController) {
    val viewModel = koinViewModel<NewPasswordViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val newPasswordState = viewModel.newPasswordState.collectAsState().value
    var showBottomSheet by remember { mutableStateOf(false) }

    NewPasswordContent(
        state = newPasswordState,
        event = viewModel::onEvent
    )

    if (uiState is UiState.Loading && uiState.isLoading) {
        // TODO: Show loading
    }

    if (uiState is UiState.Success<*>) {
        LaunchedEffect(Unit) {
            showBottomSheet = true
        }
    }

    if (uiState is UiState.ErrorState) {
        // TODO: Show error
    }

    if (showBottomSheet) {
        CommonBottomSheet(
            icon = R.drawable.thumbnail_check_circle,
            title = R.string.text_password_changed_successfully,
            message = R.string.text_password_reset_success_message,
            textPositiveButton = R.string.text_action_login,
            onPositiveButtonClicked = {
                showBottomSheet = false
                navigateToLogin(navController)
            },
            onDismissRequest = {
                showBottomSheet = false
                navigateToLogin(navController)
            }
        )
    }
}

@Composable
fun NewPasswordContent(
    state: NewPasswordState,
    event: (NewPasswordEvent) -> Unit
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
                .padding(horizontal = 24._dpw)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.thumbnail_padlock),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(16._dph))

            TextComponent(
                text = stringResource(R.string.text_create_new_password),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(R.string.text_ready_to_create_password),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            CredentialComponent(
                title = stringResource(R.string.text_new_password),
                titleColor = if (state.passwordError != null) Red60 else null,
                placeHolder = stringResource(R.string.text_password_placeholder),
                isPassword = true,
                isError = state.passwordError != null,
                value = state.password,
                onValueChange = { event(NewPasswordEvent.PasswordChanged(it)) },
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
                onValueChange = { event(NewPasswordEvent.ConfirmPasswordChanged(it)) },
            )

            Spacer(modifier = Modifier.height(40._dph))

            FilledRoundButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60._dpw),
                text = stringResource(R.string.text_save_password),
                enabled = state.password.isNotEmpty() && state.confirmPassword.isNotEmpty(),
                onClick = { event(NewPasswordEvent.SavePassword) }
            )
        }
    }
}

@Preview
@Composable
private fun NewPasswordPreview() {
    MeuCaminhaoTheme {
        NewPasswordContent(
            state = NewPasswordState(),
            event = {}
        )
    }
}

private fun navigateToLogin(navController: NavController) {
    navController.navigate(Routes.LoginScreen.route) {
        popUpTo(navController.graph.startDestinationId) { inclusive = true }
    }
}