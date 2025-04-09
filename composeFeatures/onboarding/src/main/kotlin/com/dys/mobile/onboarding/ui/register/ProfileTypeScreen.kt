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
import com.dys.mobile.meucaminhao.domain.enum.UserProfileTypeEnum
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.cards.CardProfileTypeComponent
import com.dys.mobile.uikit.components.enum.ProfileTypeInfoEnum
import com.dys.mobile.uikit.components.stepper.StepperComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileTypeScreen(navController: NavController) {
    val viewModel = koinViewModel<RegisterViewModel>()
    val registerState = viewModel.registerState.collectAsState().value

    ProfileTypeScreenContent(
        state = registerState,
        event = viewModel::onProfileTypeEvent
    )
    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun ProfileTypeScreenContent(
    state: RegisterState = RegisterState(),
    event: (RegisterEvent) -> Unit = {}
) {
    val textButton = when (state.profileType) {
        UserProfileTypeEnum.BETA -> stringResource(R.string.text_get_started)
        else -> stringResource(R.string.common_text_advance)
    }

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
                            .padding(horizontal = 60._dpw),
                        text = textButton,
                        enabled = state.profileType != UserProfileTypeEnum.UNDEFINED,
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
            StepperComponent(
                currentStep = 2,
                textSteps = listOf(
                    stringResource(R.string.text_step_create_account),
                    stringResource(R.string.text_step_account_type),
                    stringResource(R.string.text_step_start_using)
                )
            )

            Spacer(modifier = Modifier.height(40._dph))

            TextComponent(
                text = stringResource(R.string.text_account_type),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(R.string.text_select_account_type_message),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))

            CardProfileTypeComponent(
                title = R.string.text_collaborator,
                message = R.string.text_collaborator_profile_info,
                isExpandable = false,
                isSelected = state.profileType == UserProfileTypeEnum.BETA,
                onClick = {
                    event(RegisterEvent.ProfileTypeChanged(UserProfileTypeEnum.BETA))
                }
            )

            Spacer(modifier = Modifier.height(16._dph))

            CardProfileTypeComponent(
                title = R.string.text_owner,
                message = R.string.text_owner_profile_info,
                isSelected = state.profileType == UserProfileTypeEnum.ALPHA,
                profileTypeInfoEnum = ProfileTypeInfoEnum.ALPHA,
                onClick = {
                    event(RegisterEvent.ProfileTypeChanged(UserProfileTypeEnum.ALPHA))
                }
            )

            Spacer(modifier = Modifier.height(32._dph))
        }
    }
}

@Preview
@Composable
private fun ProfileTypeScreenPreview() {
    MeuCaminhaoTheme {
        ProfileTypeScreenContent()
    }
}