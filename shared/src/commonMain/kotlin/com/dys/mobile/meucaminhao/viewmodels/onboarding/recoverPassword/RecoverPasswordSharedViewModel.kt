package com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecoverPasswordSharedViewModel(private val credentialsValidator: CredentialsValidatorUseCase) :
    BaseViewModel() {

    private var _recoverPasswordStateFlow = MutableStateFlow(RecoverPasswordState())
    val recoverPasswordState = _recoverPasswordStateFlow.asStateFlow()

    fun onEvent(event: RecoverPasswordEvent) {
        when (event) {
            is RecoverPasswordEvent.EmailChanged -> {
                _recoverPasswordStateFlow.updateEmail(event.email)
            }
            RecoverPasswordEvent.SendCode -> {
                validateEmail()
            }
        }
    }

    private fun validateEmail() {
        val email = _recoverPasswordStateFlow.value.email

        if (!credentialsValidator.isEmailValid(email)) {
            _recoverPasswordStateFlow.updateEmailError(CredentialsErrorState.InvalidEmail)
            return
        }

        _recoverPasswordStateFlow.updateEmailError(null)
        sendCode()
    }

    private fun sendCode() {
        // TODO: Send code
    }
}