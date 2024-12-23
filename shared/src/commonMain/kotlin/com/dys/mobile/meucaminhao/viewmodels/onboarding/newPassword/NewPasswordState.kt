package com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class NewPasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: PasswordError? = null,
)

sealed interface PasswordError {
    data object PasswordTooShort : PasswordError
    data object PasswordsDoNotMatch : PasswordError
}

fun MutableStateFlow<NewPasswordState>.updatePassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<NewPasswordState>.updateConfirmPassword(confirmPassword: String) {
    update { it.copy(confirmPassword = confirmPassword) }
}

fun MutableStateFlow<NewPasswordState>.updatePasswordError(error: PasswordError?) {
    update { it.copy(passwordError = error) }
}