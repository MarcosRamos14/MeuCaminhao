package com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class NewPasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: CredentialsErrorState? = null,
    val isBottomSheetVisible: Boolean = false
)

fun MutableStateFlow<NewPasswordState>.updatePassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<NewPasswordState>.updateConfirmPassword(confirmPassword: String) {
    update { it.copy(confirmPassword = confirmPassword) }
}

fun MutableStateFlow<NewPasswordState>.updatePasswordError(error: CredentialsErrorState?) {
    update { it.copy(passwordError = error) }
}

fun MutableStateFlow<NewPasswordState>.updateBottomSheetVisibility(isVisible: Boolean) {
    update { it.copy(isBottomSheetVisible = isVisible) }
}