package com.dys.mobile.meucaminhao.domain.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DefaultUiStateManager : UiStateManager {

    private val _uiStateFlow = MutableStateFlow<UiState>(UiState.Loading(false))
    override val uiState: StateFlow<UiState> = _uiStateFlow

    override fun emitState(state: UiState) {
        _uiStateFlow.tryEmit(state)
    }
}