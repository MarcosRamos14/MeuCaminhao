package com.dys.mobile.home.ui

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
import com.dys.mobile.meucaminhao.viewmodels.login.LoginEvent
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.appBar.TopAppBarComponent
import com.dys.mobile.uikit.components.bottomBar.BottomAppBarComponent
import com.dys.mobile.uikit.components.buttons.FilledRoundButtonComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun HomeScreen(navController: NavController) {
    HomeContent()
}

@Composable
fun HomeContent() {
    val nameTest = "Marcos"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.text_home_screen_title, nameTest) // TODO: Add dynamic name
            )
        },
        bottomBar = {
            BottomAppBarComponent()
        },
        containerColor = MaterialTheme.colorScheme.onBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24._dpw)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            FilledRoundButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.text_new_trip),
                icon = R.drawable.ic_add,
                onClick = {
                    // TODO: New trip
                }
            )

            Spacer(modifier = Modifier.height(16._dph))
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MeuCaminhaoTheme {
        HomeContent()
    }
}