package com.dys.mobile.vehicles.ui.vehicleDetails.pages

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.navigation.routes.Routes.EditVehicleScreen
import com.dys.mobile.meucaminhao.navigation.routes.Routes.FullPhotoScreen
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.CloseConfirmationDeletionBottomSheet
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.CloseDeletionBottomSheet
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.DeleteVehicle
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.OpenDeletionBottomSheet
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.RequestGeneralVehicleInfo
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoState
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.bottomSheet.CommonBottomSheet
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.components.cards.CardExpandableCountComponent
import com.dys.mobile.uikit.components.cards.CardLinkedChecklistsComponent
import com.dys.mobile.uikit.components.cards.CardLinkedDriversComponent
import com.dys.mobile.uikit.components.cards.CardMetricComponent
import com.dys.mobile.uikit.components.cards.CardVehicleGeneralInfoComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Red60
import org.koin.androidx.compose.koinViewModel

@Composable
fun GeneralVehicleInfoPage(navController: NavController, id: Long) {
    val viewModel = koinViewModel<GeneralVehicleInfoViewModel>()
    val generalVehicleInfoState = viewModel.generalVehicleInfoState.collectAsState().value

    GeneralVehicleInfoContent(
        id = id,
        navController = navController,
        state = generalVehicleInfoState,
        event = viewModel::onEvent
    )

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun GeneralVehicleInfoContent(
    id: Long,
    navController: NavController,
    state: GeneralVehicleInfoState,
    event: (Event) -> Unit
) {
    event(RequestGeneralVehicleInfo(id))

    var driverExpanded by remember { mutableStateOf(false) }
    var checklistExpanded by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.onBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16._dpw)
                .verticalScroll(rememberScrollState())
        ) {
            TextComponent(
                modifier = Modifier.padding(top = 24._dph, bottom = 16._dph),
                text = stringResource(R.string.common_general_information),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleSmall
            )

            val history = state.vehicleInfo?.history

            if (!history.isNullOrEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(8._dpw)
                ) {
                    items(history) { item ->
                        val accentColor = item.accentColor
                            ?.toLongOrNull(16)
                            ?.let { Color(it) }
                            ?: Black

                        CardMetricComponent(
                            value = item.value,
                            overline = item.overline,
                            valueColor = accentColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16._dph))
            }

            CardVehicleGeneralInfoComponent(
                vehicleInfo = state.vehicleInfo,
                openPhotoClick = { url ->
                    event(
                        NavigateTo(
                            FullPhotoScreen.routeWithArgs(Uri.encode(url))
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(16._dph))

            val drivers = state.vehicleInfo?.drivers

            if (!drivers.isNullOrEmpty()) {
                CardExpandableCountComponent(
                    title = R.string.text_linked_drivers,
                    listSize = drivers.size,
                    isExpanded = driverExpanded,
                    onExpandToggle = { driverExpanded = !driverExpanded },
                    content = {
                        Column {
                            drivers.forEach { driver ->
                                CardLinkedDriversComponent(
                                    driver = driver,
                                    onClick = {
                                        // TODO: Navegar para gestão de motorista
                                    }
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8._dph))
            }

            val checklists = state.vehicleInfo?.checklists

            if (!checklists.isNullOrEmpty()) {
                CardExpandableCountComponent(
                    title = R.string.text_linked_checklists,
                    listSize = checklists.size,
                    isExpanded = checklistExpanded,
                    onExpandToggle = { checklistExpanded = !checklistExpanded },
                    content = {
                        Column {
                            checklists.forEach { checklist ->
                                CardLinkedChecklistsComponent(
                                    checklist = checklist,
                                    onClick = {
                                        // TODO: Navegar para gestão de checklist
                                    }
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16._dph))
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8._dpw)
            ) {
                OutlinedRoundButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.text_delete_vehicle),
                    fontWeight = FontWeight.Bold,
                    color = Red60,
                    onClick = { event(OpenDeletionBottomSheet) }
                )

                FilledRoundButtonComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.text_edit_vehicle),
                    onClick = {
                        event(NavigateTo(EditVehicleScreen.routeWithArgs(id)))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16._dph))
        }
    }

    if (state.openDeletionBottomSheet) {
        CommonBottomSheet(
            title = R.string.text_exclude_vehicle,
            message = R.string.text_vehicle_deletion_message,
            isMultipleOptions = true,
            textPositiveButton = R.string.text_delete_vehicle,
            textNegativeButton = R.string.common_text_cancel,
            onPositiveButtonClicked = { event(DeleteVehicle(id)) },
            onNegativeButtonClicked = { event(CloseDeletionBottomSheet) },
            onDismissRequest = { event(CloseDeletionBottomSheet) }
        )
    }

    if (state.openConfirmationDeletionBottomSheet) {
        CommonBottomSheet(
            icon = R.drawable.thumbnail_check_circle,
            title = R.string.text_excluded_vehicle,
            message = R.string.text_vehicle_removed_successfully,
            textPositiveButton = R.string.common_text_ok,
            onPositiveButtonClicked = {
                event(CloseConfirmationDeletionBottomSheet)
                navController.popBackStack()
            },
            onDismissRequest = {
                event(CloseConfirmationDeletionBottomSheet)
                navController.popBackStack()
            }
        )
    }
}

@Preview
@Composable
private fun GeneralVehicleInfoPreview() {
    MeuCaminhaoTheme {
        GeneralVehicleInfoContent(
            id = 0,
            navController = NavController(LocalContext.current),
            state = GeneralVehicleInfoState(),
            event = { }
        )
    }
}