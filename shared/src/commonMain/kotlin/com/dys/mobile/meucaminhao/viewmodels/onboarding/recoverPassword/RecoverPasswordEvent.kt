package com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword

sealed interface RecoverPasswordEvent {
    data class CredentialChanged(val credential: String) : RecoverPasswordEvent
    data object SendCode : RecoverPasswordEvent
    data class ReceiveSms(val receiveSms: Boolean) : RecoverPasswordEvent
}