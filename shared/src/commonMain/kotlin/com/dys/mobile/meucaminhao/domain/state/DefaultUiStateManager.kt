package com.dys.mobile.meucaminhao.domain.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DefaultUiStateManager : UiStateManager {

    private val _uiStateFlow = MutableStateFlow(UiState())
    override val uiState: StateFlow<UiState> = _uiStateFlow

    override fun updateState(fn: (UiState) -> UiState) {
        _uiStateFlow.update(fn)
    }
}