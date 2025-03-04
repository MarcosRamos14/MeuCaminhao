package com.dys.mobile.meucaminhao.viewmodels

import com.dys.mobile.meucaminhao.domain.state.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class ExpectBaseViewModel {

    actual val scope: CoroutineScope = MainScope()

    actual fun emitState(state: UiState) {
        TODO()
    }

    protected actual open fun onCleared() {
        TODO()
    }

    fun clear() {
        onCleared()
        scope.cancel()
    }
}