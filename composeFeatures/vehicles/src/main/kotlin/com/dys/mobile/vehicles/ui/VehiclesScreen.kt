package com.dys.mobile.vehicles.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun VehiclesScreen() {
    VehiclesContent()
}

@Composable
private fun VehiclesContent() {
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
                .padding(24._dph),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_construction),
                contentDescription = null,
                tint = Color.Unspecified
            )

            TextComponent(
                text = "Tela de veículos",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            TextComponent(
                text = "Em desenvolvimento",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun VehiclesContentPreview() {
    MeuCaminhaoTheme { 
        VehiclesContent()
    }
}