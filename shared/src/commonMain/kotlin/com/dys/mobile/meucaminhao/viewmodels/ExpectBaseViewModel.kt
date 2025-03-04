package com.dys.mobile.meucaminhao.viewmodels

import com.dys.mobile.meucaminhao.domain.state.UiState
import kotlinx.coroutines.CoroutineScope

expect abstract class ExpectBaseViewModel() {
    val scope: CoroutineScope

    fun emitState(state: UiState)

    protected open fun onCleared()
}