package com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class VerifyCodeState(
    val code: List<Int?> = (1..6).map { null },
    val focusedIndex: Int? = null,
    val codeError: CredentialsErrorState? = null,
)

fun MutableStateFlow<VerifyCodeState>.updateCode(code: List<Int?>) {
    update { it.copy(code = code) }
}

fun MutableStateFlow<VerifyCodeState>.updateFocusedIndex(index: Int?) {
    update { it.copy(focusedIndex = index) }
}

fun MutableStateFlow<VerifyCodeState>.updateCodeError(error: CredentialsErrorState?) {
    update { it.copy(codeError = error) }
}