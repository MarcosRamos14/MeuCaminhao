package com.dys.mobile.meucaminhao.domain.state

import com.dys.mobile.meucaminhao.domain.dto.ErrorDTO

data class UiState(
    val isLoading: Boolean = false,
    val error: SingleEvent<ErrorDTO>? = null,
    val data: Any? = null,
    val navigation: SingleEvent<String>? = null
)