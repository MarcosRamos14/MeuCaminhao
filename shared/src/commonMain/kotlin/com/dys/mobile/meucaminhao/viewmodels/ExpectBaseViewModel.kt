package com.dys.mobile.meucaminhao.viewmodels

import kotlinx.coroutines.CoroutineScope

expect abstract class ExpectBaseViewModel() {
    val scope: CoroutineScope

    protected open fun onCleared()
}
