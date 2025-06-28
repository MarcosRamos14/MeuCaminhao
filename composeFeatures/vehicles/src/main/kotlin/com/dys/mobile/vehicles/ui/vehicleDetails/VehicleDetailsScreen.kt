package com.dys.mobile.vehicles.ui.vehicleDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun VehicleDetailsScreen(vehicleId: Long) {
    VehicleDetailsContent()
}

@Composable
fun VehicleDetailsContent() {

}

@Preview
@Composable
fun VehicleDetailsPreview() {
    MeuCaminhaoTheme {
        VehicleDetailsContent()
    }
}