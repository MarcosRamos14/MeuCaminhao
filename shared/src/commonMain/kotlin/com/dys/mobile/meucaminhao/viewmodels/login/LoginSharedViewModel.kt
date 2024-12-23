package com.dys.mobile.meucaminhao.viewmodels.login

import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.viewmodels.BaseSharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginSharedViewModel(
    private val credentialsValidator: CredentialsValidatorUseCase
) : BaseSharedViewModel() {

    private var _loginStateFlow = MutableStateFlow(LoginState())
    val loginState = _loginStateFlow.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _loginStateFlow.updateEmail(event.email)
            }
            is LoginEvent.PasswordChanged -> {
                _loginStateFlow.updatePassword(event.password)
            }
            LoginEvent.ForgotPassword -> {
                TODO()
            }
            LoginEvent.Access -> {
                TODO()
            }
            LoginEvent.AccessWithGoogle -> {
                TODO()
            }
            LoginEvent.Register -> {
                TODO()
            }
        }
    }
}