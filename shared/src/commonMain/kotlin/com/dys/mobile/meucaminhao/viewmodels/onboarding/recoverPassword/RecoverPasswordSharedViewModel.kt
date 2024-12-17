package com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword

import com.dys.mobile.meucaminhao.viewmodels.BaseSharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecoverPasswordSharedViewModel : BaseSharedViewModel() {

    private var _recoverPasswordStateFlow = MutableStateFlow(RecoverPasswordState())
    val recoverPasswordState = _recoverPasswordStateFlow.asStateFlow()

    fun onEvent(event: RecoverPasswordEvent) {
        when (event) {
            is RecoverPasswordEvent.CredentialChanged -> {
                _recoverPasswordStateFlow.updateCredential(event.credential)
            }
            RecoverPasswordEvent.SendCode -> {
                TODO()
            }
            is RecoverPasswordEvent.ReceiveSms -> {
                _recoverPasswordStateFlow.updateReceiveSms(event.receiveSms)
            }
        }
    }
}