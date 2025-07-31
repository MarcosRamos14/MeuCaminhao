package com.dys.mobile.vehicles.ui.vehicleDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.chips.FilterChipGroupComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.vehicles.ui.vehicleDetails.VehicleDetailPagesEnum.CHECKLIST_HISTORY
import com.dys.mobile.vehicles.ui.vehicleDetails.VehicleDetailPagesEnum.EXTRA_EXPENSES
import com.dys.mobile.vehicles.ui.vehicleDetails.VehicleDetailPagesEnum.GENERAL_INFO
import com.dys.mobile.vehicles.ui.vehicleDetails.VehicleDetailPagesEnum.TRAVEL_EXPENSES
import com.dys.mobile.vehicles.ui.vehicleDetails.pages.GeneralVehicleInfoPage
import com.dys.mobile.vehicles.ui.vehicleDetails.pages.VehicleChecklistHistoryPage
import com.dys.mobile.vehicles.ui.vehicleDetails.pages.VehicleExtraExpensesPage
import com.dys.mobile.vehicles.ui.vehicleDetails.pages.VehicleTravelExpensesPage
import kotlinx.coroutines.launch

@Composable
fun VehicleDetailsScreen(
    navController: NavController,
    id: Long,
    licensePlate: String
) {
    val coroutineScope = rememberCoroutineScope()
    val pages = VehicleDetailPagesEnum.entries.toTypedArray()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(
                modifier = Modifier,
                title = stringResource(R.string.text_vehicle, licensePlate),
                hasDivider = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            FilterChipGroupComponent(
                options = pages,
                selectedOption = pages[pagerState.currentPage],
                getLabelRes = { it.label },
                onOptionSelected = { selected ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pages.indexOf(selected))
                    }
                }
            )

            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (pages[page]) {
                    GENERAL_INFO -> GeneralVehicleInfoPage(navController, id)
                    TRAVEL_EXPENSES -> VehicleTravelExpensesPage(id)
                    EXTRA_EXPENSES -> VehicleExtraExpensesPage(id)
                    CHECKLIST_HISTORY -> VehicleChecklistHistoryPage(id)
                }
            }
        }
    }
}

@Preview
@Composable
private fun VehicleDetailsPreview() {
    MeuCaminhaoTheme {
        VehicleDetailsScreen(
            navController = NavController(LocalContext.current),
            id = 1,
            licensePlate = "ABC-1234"
        )
    }
}