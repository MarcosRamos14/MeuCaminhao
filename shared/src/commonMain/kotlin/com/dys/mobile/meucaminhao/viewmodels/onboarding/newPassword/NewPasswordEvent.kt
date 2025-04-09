package com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword

import com.dys.mobile.meucaminhao.navigation.event.Event

sealed interface NewPasswordEvent : Event {
    data class PasswordChanged(val password: String) : NewPasswordEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : NewPasswordEvent
    data object SavePassword : NewPasswordEvent
}