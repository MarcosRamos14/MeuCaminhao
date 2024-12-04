package com.dys.mobile.meucaminhao.viewmodels.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)

fun MutableStateFlow<LoginState>.updateEmail(email: String) {
    update { it.copy(email = email) }
}

fun MutableStateFlow<LoginState>.updateEmailError(message: String) {
    update { it.copy(emailError = message) }
}

fun MutableStateFlow<LoginState>.updatePassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<LoginState>.updatePasswordError(message: String) {
    update { it.copy(emailError = message) }
}