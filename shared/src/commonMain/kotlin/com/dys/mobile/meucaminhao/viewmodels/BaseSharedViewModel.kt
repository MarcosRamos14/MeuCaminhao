package com.dys.mobile.meucaminhao.viewmodels

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseSharedViewModel() {
    val scope: CoroutineScope
    protected open fun onCleared()
}