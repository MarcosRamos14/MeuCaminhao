package com.dys.mobile.toolkit.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dys.mobile.meucaminhao.domain.state.UiStateManager

@Composable
fun CollectUiState(manager: UiStateManager, onNavigationEvent: (String) -> Unit = {}) {
    val uiState by manager.uiState.collectAsState()

    if (uiState.isLoading) {
        //TODO: Show loading (enabled or disabled)
    }

    uiState.navigation?.valueOrNull?.let { route ->
        onNavigationEvent(route)
    }

    uiState.error?.valueOrNull?.let { error ->
        //TODO: Show error
    }
}