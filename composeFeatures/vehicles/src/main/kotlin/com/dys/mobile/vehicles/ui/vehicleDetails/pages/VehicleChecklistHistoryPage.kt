package com.dys.mobile.vehicles.ui.vehicleDetails.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun VehicleChecklistHistoryPage(id: Long) {
    VehicleChecklistHistoryContent()
}

@Composable
private fun VehicleChecklistHistoryContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16._dpw),
        color = MaterialTheme.colorScheme.onBackground
    ) {
        TextComponent(
            modifier = Modifier.padding(top = 24._dph),
            text = stringResource(R.string.text_checklist_history),
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
private fun VehicleChecklistHistoryPreview() {
    MeuCaminhaoTheme {
        VehicleChecklistHistoryContent()
    }
}