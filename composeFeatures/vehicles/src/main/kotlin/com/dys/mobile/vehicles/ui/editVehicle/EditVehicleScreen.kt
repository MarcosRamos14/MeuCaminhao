package com.dys.mobile.vehicles.ui.editVehicle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun EditVehicleScreen(id: Long) {

    EditVehicleContent()
}

@Composable
private fun EditVehicleContent(

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBarComponent(
                modifier = Modifier,
                title = stringResource(R.string.text_edit_vehicle)
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
private fun EditVehiclePreview() {
    MeuCaminhaoTheme {
        EditVehicleContent(

        )
    }
}