package com.dys.mobile.trips.ui.tripDetails

import android.net.Uri
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
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.checklistStatusEnum
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum.COMPLETED
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum.INCOMPLETE
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum.UNREALIZED
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.trips.details.TripDetailsViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
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
fun TripDetailsScreen(tripId: Long, navController: NavController) {
    val viewModel = koinViewModel<TripDetailsViewModel>()

    viewModel.requestTripById(tripId = tripId)

    TripDetailsContent(
        mockTrip = viewModel.getMockList(), // TODO: Switch to real data
        event = viewModel::onEvent
    )

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun TripDetailsContent(
    mockTrip: TripDTO,
    event: (Event) -> Unit
) {
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
                income = mockTrip.income,
                openManifestImageClick = {
                    navigateToFullPhotoScreen(
                        uri = mockTrip.generalInformation?.manifestUrl ?: "",
                        event = event
                    )
                }
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
                tripExpenses = mockTrip.expenses,
                openPhotoTicketClick = { uri ->
                    navigateToFullPhotoScreen(uri, event)
                },
                openAdditionalPhotosClick = {
                    event(NavigateTo(
                        Routes.PhotoGalleryScreen.routeWithArgs(
                            Uri.encode(it)
                        )
                    ))
                },
                deleteExpenseClick = {
                    // TODO: Delete expense
                },
                editExpenseClick = {
                    // TODO: Edit expense
                },
                newExpenseClick = {
                    // TODO: New Expense Screen
                }
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
                           // TODO: Pendente de implementação para tela de checklist.
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
                        text = stringResource(R.string.text_delete_trip),
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

private fun navigateToFullPhotoScreen(uri: String, event: (Event) -> Unit) {
    event(NavigateTo(
        Routes.FullPhotoScreen.routeWithArgs(
            Uri.encode(uri)
        )
    ))
}

@Preview
@Composable
private fun TripDetailsContentPreview() {
    MeuCaminhaoTheme {
        TripDetailsContent(
            mockTrip = TripDTO(
                id = 123,
                title = "",
                generalInformation = null,
                income = null,
                expenses = null,
                checklist = emptyList(),
                canEdit = true,
                canDelete = true
            ),
            event = {  }
        )
    }
}