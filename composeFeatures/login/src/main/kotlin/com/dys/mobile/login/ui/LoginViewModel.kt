package com.dys.mobile.login.ui

import com.dys.mobile.meucaminhao.viewmodels.LoginSharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class LoginViewModel : LoginSharedViewModel() {

    private val _uiStateFlow = MutableStateFlow(LoginState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _uiStateFlow.update { it.copy(email = event.email) }
            }
            is LoginEvent.PasswordChanged -> {
                _uiStateFlow.update { it.copy(password = event.password) }
            }
            LoginEvent.ForgotPassword -> TODO()
            LoginEvent.Access -> TODO()
            LoginEvent.AccessWithGoogle -> TODO()
            LoginEvent.Register -> TODO()
        }
    }
}