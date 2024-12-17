package com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class RecoverPasswordState(
    val credential: String = "",
    val credentialError: String? = null,
    val receiveSms: Boolean = false
)

fun MutableStateFlow<RecoverPasswordState>.updateCredential(credential: String) {
    update { it.copy(credential = credential) }
}

fun MutableStateFlow<RecoverPasswordState>.updateCredentialError(message: String) {
    update { it.copy(credentialError = message) }
}

fun MutableStateFlow<RecoverPasswordState>.updateReceiveSms(receiveSms: Boolean) {
    update { it.copy(receiveSms = receiveSms) }
}