package com.dys.mobile.meucaminhao.domain.state

import com.dys.mobile.meucaminhao.domain.dto.ErrorDTO
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface UiStateManager {

    val uiState: StateFlow<UiState>

    fun updateState(fn: (UiState) -> UiState)
}

fun UiStateManager.launchWithState(updateLoading: Boolean = true, action: suspend () -> Unit) {
    scope?.launch {
        runCatching {
            if (updateLoading) enableLoading()
            action.invoke()
        }.onFailure { throwable ->
            (throwable as? ErrorDTO)?.let { error ->
                updateState { state ->
                    state.copy(error = error.asSingleEvent())
                }
            }
        }.also {
            disableLoading()
        }
    }
}

val UiStateManager.scope: CoroutineScope? get() = when(this) {
    is BaseViewModel -> scope
    else -> null
}

fun UiStateManager.enableLoading() {
    updateState { state ->
        state.copy(isLoading = true)
    }
}

fun UiStateManager.disableLoading() {
    updateState { state ->
        state.copy(isLoading = false)
    }
}