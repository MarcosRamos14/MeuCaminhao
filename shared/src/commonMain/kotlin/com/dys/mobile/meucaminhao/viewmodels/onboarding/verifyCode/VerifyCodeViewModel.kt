package com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import com.dys.mobile.meucaminhao.domain.state.SingleEvent
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val VALID_OTP_CODE_TEST = "123456"

class VerifyCodeViewModel : BaseViewModel() {

    private val _verifyCodeStateFlow = MutableStateFlow(VerifyCodeState())
    val verifyCodeState = _verifyCodeStateFlow.asStateFlow()

    fun onEvent(event: VerifyCodeEvent) {
        when (event) {
            is VerifyCodeEvent.NumberChanged -> {
                val newCode = _verifyCodeStateFlow.value.code.toMutableList()
                newCode[event.index] = event.number
                _verifyCodeStateFlow.updateCode(newCode)
            }
            is VerifyCodeEvent.ChangeFieldFocused -> {
                _verifyCodeStateFlow.updateFocusedIndex(event.index)
            }
            VerifyCodeEvent.VerifyCode -> {
                validateCode()
            }
            VerifyCodeEvent.NoCodeReceived -> {
                TODO()
            }
        }
    }

    private fun validateCode() {
        val code = _verifyCodeStateFlow.value.code

        // TODO: Verificar se código não está expirado.

        if (code.joinToString("") != VALID_OTP_CODE_TEST) {
            _verifyCodeStateFlow.updateCodeError(CredentialsErrorState.InvalidVerificationCode)
            return
        }

        _verifyCodeStateFlow.updateCodeError(null)
        emitState(UiState.Navigation(
            SingleEvent(Routes.NewPasswordScreen.route)
        ))
    }
}