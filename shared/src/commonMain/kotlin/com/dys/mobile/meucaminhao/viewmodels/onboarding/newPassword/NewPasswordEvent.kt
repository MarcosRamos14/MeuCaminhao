package com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword

sealed interface NewPasswordEvent {
    data class PasswordChanged(val password: String) : NewPasswordEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : NewPasswordEvent
    data object SavePassword : NewPasswordEvent
}