package com.dys.mobile.vehicles.ui.myVehicles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles.MyVehiclesEvent.RequestMyVehicles
import com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles.MyVehiclesState
import com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles.MyVehiclesViewModel
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.cards.CardVehicleComponent
import com.dys.mobile.uikit.components.search.SearchBarComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

const val LICENSE_PLATE_SIZE = 7

@Composable
fun MyVehiclesScreen(navController: NavController) {
    val viewModel = koinViewModel<MyVehiclesViewModel>()
    val myVehiclesState = viewModel.myVehiclesState.collectAsState().value

    MyVehiclesContent(
        myVehiclesState = myVehiclesState,
        event = viewModel::onEvent
    )

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
fun MyVehiclesContent(
    myVehiclesState: MyVehiclesState,
    event: (Event) -> Unit
) {
    event(RequestMyVehicles)

    var queryState by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(
                modifier = Modifier,
                title = stringResource(R.string.text_my_vehicles),
                hasDivider = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SearchBarComponent(
                query = queryState,
                onQueryChange = { queryState = it },
                placeHolder = R.string.text_search_by_plate,
                uppercase = true,
                maxLength = LICENSE_PLATE_SIZE,
                hasDividerTop = false
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(8._dph))
                }

                items(myVehiclesState.vehicles) { vehicle ->
                    CardVehicleComponent(
                        vehicle = vehicle,
                        onClick = {
                            event(NavigateTo(Routes.VehicleDetailsScreen.routeWithArgs(
                                id = vehicle.id,
                                licensePlate = vehicle.plate
                            )))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyVehiclesPreview() {
    MeuCaminhaoTheme {
        MyVehiclesContent(
            myVehiclesState = MyVehiclesState(),
            event = { }
        )
    }
}