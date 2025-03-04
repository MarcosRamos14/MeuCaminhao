package com.dys.mobile.meucaminhao.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual abstract class ExpectBaseViewModel : ViewModel() {

    actual val scope = viewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}