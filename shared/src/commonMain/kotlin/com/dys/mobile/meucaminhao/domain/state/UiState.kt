package com.dys.mobile.meucaminhao.domain.state

import com.dys.mobile.meucaminhao.domain.dto.ErrorDTO

abstract class UiState {
    class Loading(val isLoading: Boolean) : UiState()
    class Success<T>(val data: T) : UiState()
    class ErrorState(val error: ErrorDTO) : UiState()
}