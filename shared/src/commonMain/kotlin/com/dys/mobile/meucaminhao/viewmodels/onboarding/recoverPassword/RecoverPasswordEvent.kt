package com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword

sealed interface RecoverPasswordEvent {
    data class EmailChanged(val email: String) : RecoverPasswordEvent
    data object SendCode : RecoverPasswordEvent
}