package com.dys.mobile.meucaminhao.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dys.mobile.meucaminhao.domain.dto.ErrorDTO
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.domain.state.UiState.Loading
import com.dys.mobile.meucaminhao.domain.state.UiStateManager
import com.dys.mobile.meucaminhao.domain.state.disableLoading
import com.dys.mobile.meucaminhao.domain.state.enableLoading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

actual abstract class BaseSharedViewModel : ViewModel(), UiStateManager {

    actual val scope = viewModelScope

    private val _uiStateFlow = MutableStateFlow<UiState>(Loading(false))
    override val uiState: StateFlow<UiState> = _uiStateFlow

    actual override fun onCleared() {
        super.onCleared()
    }

    override fun emitState(state: UiState) {
        _uiStateFlow.tryEmit(state)
    }

    override fun launchWithState(updateLoading: Boolean, action: suspend () -> Unit) {
        scope.launch {
            if (updateLoading) enableLoading()
            runCatching {
                action.invoke()
            }.onFailure {
                emitState(UiState.ErrorState(ErrorDTO("", "")))
            }.also {
                disableLoading()
            }
        }
    }
}