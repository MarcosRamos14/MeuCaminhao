package com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class RecoverPasswordState(
    val email: String = "",
    val emailError: CredentialsErrorState? = null
)

fun MutableStateFlow<RecoverPasswordState>.updateEmail(email: String) {
    update { it.copy(email = email) }
}

fun MutableStateFlow<RecoverPasswordState>.updateEmailError(error: CredentialsErrorState?) {
    update { it.copy(emailError = error) }
}