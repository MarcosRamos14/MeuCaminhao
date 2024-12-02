package com.dys.mobile.meucaminhao.domain.state

import kotlinx.coroutines.flow.StateFlow

interface UiStateManager {

    val uiState: StateFlow<UiState>

    fun emitState(state: UiState)

    fun launchWithState(updateLoading: Boolean = true, action: suspend () -> Unit)
}

fun UiStateManager.enableLoading() {
    emitState(UiState.Loading(true))
}

fun UiStateManager.disableLoading() {
    emitState(UiState.Loading(false))
}