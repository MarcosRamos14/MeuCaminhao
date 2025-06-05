package com.dys.mobile.trips.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.checklistStatusEnum
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum.COMPLETED
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum.INCOMPLETE
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum.UNREALIZED
import com.dys.mobile.meucaminhao.viewmodels.trips.TripDetailsViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.cards.CardExpensesComponent
import com.dys.mobile.uikit.components.cards.CardTotalValueComponent
import com.dys.mobile.uikit.components.cards.CardTripChecklistComponent
import com.dys.mobile.uikit.components.cards.CardTripGeneralInfoComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripDetailsScreen() {
    val viewModel = koinViewModel<TripDetailsViewModel>()

    TripDetailsContent(viewModel.getMockList())
}

@Composable
private fun TripDetailsContent(mockTrip: TripDTO) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = mockTrip.title
            )
        },
        containerColor = MaterialTheme.colorScheme.onBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(innerPadding)
                .padding(horizontal = 24._dpw)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24._dph))

            TextComponent(
                text = stringResource(R.string.common_general_information),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(16._dph))

            CardTripGeneralInfoComponent(
                generalInfo = mockTrip.generalInformation,
                income = mockTrip.income
            )

            Spacer(modifier = Modifier.height(24._dph))

            TextComponent(
                text = stringResource(R.string.common_result),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(16._dph))

            CardTotalValueComponent(
                isExpense = false,
                title = R.string.text_net_worth,
                amount = mockTrip.income?.totalAmount
            )

            Spacer(modifier = Modifier.height(24._dph))

            TextComponent(
                text = stringResource(R.string.text_expenses),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(16._dph))

            CardExpensesComponent(
                tripExpenses = mockTrip.expenses
            )

            Spacer(modifier = Modifier.height(24._dph))

            if (!mockTrip.checklist.isNullOrEmpty()) {
                TextComponent(
                    text = stringResource(R.string.common_checklist),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleSmall
                )

                mockTrip.checklist.orEmpty().forEach { item ->
                    val status = item.checklistStatusEnum
                    val (iconRes, contentDescription) = when (status) {
                        COMPLETED -> R.drawable.ic_checked to R.string.text_complete_checklist
                        INCOMPLETE -> R.drawable.ic_warning_checked to R.string.text_incomplete_checklist
                        UNREALIZED -> R.drawable.ic_not_checked to R.string.text_checklist_not_carried_out
                        else -> R.drawable.ic_uncknow to R.string.text_checklist_error
                    }

                    Spacer(modifier = Modifier.height(16._dph))

                    CardTripChecklistComponent(
                        name = item.title ?: stringResource(R.string.common_error_getting_name),
                        icon = iconRes,
                        contentDescription = contentDescription,
                        onClick = {
                            //TODO: Chamar módulo de checklist
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24._dph))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8._dpw)
            ) {
                if (mockTrip.canDelete == true) {
                    OutlinedRoundButtonComponent(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.common_delete),
                        fontWeight = FontWeight.Bold,
                        color = Red60,
                        onClick = {
                            // TODO: Excluir viagem
                        }
                    )
                }

                if (mockTrip.canEdit == true) {
                    FilledRoundButtonComponent(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.text_edit_trip),
                        onClick = {
                            // TODO: Editar viagem
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24._dph))
        }
    }
}

@Preview
@Composable
private fun TripDetailsContentPreview() {
    MeuCaminhaoTheme {
        TripDetailsScreen()
    }
}