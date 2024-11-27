package com.dys.mobile.meucaminhao.viewmodels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class BaseSharedViewModel {

    actual val scope: CoroutineScope = MainScope()

    protected actual open fun onCleared() {
        // TODO
    }

    fun clear() {
        onCleared()
        scope.cancel()
    }
}