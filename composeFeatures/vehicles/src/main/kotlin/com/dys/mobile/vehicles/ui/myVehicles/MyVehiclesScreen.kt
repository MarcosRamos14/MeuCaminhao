package com.dys.mobile.vehicles.ui.myVehicles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles.MyVehiclesViewModel
import com.dys.mobile.toolkit.extensions.handleRoute
import com.dys.mobile.toolkit.state.CollectUiState
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyVehiclesScreen(navController: NavController) {
    val viewModel = koinViewModel<MyVehiclesViewModel>()

    MyVehiclesContent()

    CollectUiState(viewModel, navController::handleRoute)
}

@Composable
fun MyVehiclesContent() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(
                modifier = Modifier,
                title = stringResource(R.string.text_my_vehicles)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}

@Preview
@Composable
private fun MyVehiclesPreview() {
    MeuCaminhaoTheme {
        MyVehiclesContent()
    }
}