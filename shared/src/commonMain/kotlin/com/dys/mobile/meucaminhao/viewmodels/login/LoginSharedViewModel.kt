package com.dys.mobile.meucaminhao.viewmodels.login

import com.dys.mobile.meucaminhao.data.authentication.AuthenticationRepository
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginSharedViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val credentialsValidator: CredentialsValidatorUseCase
) : BaseViewModel() {

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
            LoginEvent.Access -> doLogin()
            LoginEvent.AccessWithGoogle -> {
                TODO()
            }
            LoginEvent.Register -> {
                TODO()
            }
        }
    }

    private fun doLogin() {
        val (email, pwd) = _loginStateFlow.emailAndPassword
        if (!credentialsValidator.isEmailValid(email) || !credentialsValidator.isPasswordValid(pwd)) {
//             todo: show error
            return
        }
        launchWithState {
            authenticationRepository.authenticate(email, pwd)
        }
    }
}