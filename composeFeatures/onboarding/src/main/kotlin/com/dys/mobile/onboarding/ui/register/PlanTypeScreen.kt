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
import com.dys.mobile.meucaminhao.domain.enum.UserProfileTypeEnum
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterEvent
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterState
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.stepper.StepperComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlanTypeScreen() {
    val viewModel = koinViewModel<RegisterViewModel>()
    val registerState = viewModel.registerState.collectAsState().value

    PlanTypeScreenContent(
        state = registerState,
        event = viewModel::onPlanTypeEvent
    )
}

@Composable
fun PlanTypeScreenContent(
    state: RegisterState = RegisterState(),
    event: (RegisterEvent) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.text_complete_registration)
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
                        text = stringResource(R.string.text_get_started),
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
            Spacer(modifier = Modifier.height(40._dph))

            StepperComponent(
                currentStep = 3,
                textSteps = listOf(
                    stringResource(R.string.text_step_create_account),
                    stringResource(R.string.text_step_account_type),
                    stringResource(R.string.text_step_start_using)
                )
            )

            Spacer(modifier = Modifier.height(40._dph))

            TextComponent(
                text = stringResource(R.string.text_start_using),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(8._dph))

            TextComponent(
                text = stringResource(R.string.text_select_plan_type_message),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32._dph))
        }
    }
}

@Preview
@Composable
private fun PlanTypeScreenPreview() {
    MeuCaminhaoTheme {
        PlanTypeScreenContent()
    }
}