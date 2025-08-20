package com.dys.mobile.vehicles.ui.vehicles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehiclesContent.VehiclesViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.components.buttons.OutlinedRoundButtonComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun VehiclesScreen(navController: NavController) {
    val viewModel = koinViewModel<VehiclesViewModel>()

    VehiclesContent(
        event = viewModel::onEvent
    )

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
private fun VehiclesContent(
    event: (Event) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(title = stringResource(R.string.text_vehicles))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 16._dph, horizontal = 16._dpw)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            FilledRoundButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.text_new_vehicle),
                icon = R.drawable.ic_add,
                onClick = {
                    event(NavigateTo(Routes.NewVehicleScreen.route))
                }
            )

            Spacer(modifier = Modifier.height(8._dph))

            OutlinedRoundButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.text_my_vehicles),
                color = MaterialTheme.colorScheme.primary,
                onClick = {
                    event(NavigateTo(Routes.MyVehiclesScreen.route))
                }
            )
        }
    }
}

@Preview
@Composable
private fun VehiclesContentPreview() {
    MeuCaminhaoTheme { 
        VehiclesContent(
            event = { }
        )
    }
}