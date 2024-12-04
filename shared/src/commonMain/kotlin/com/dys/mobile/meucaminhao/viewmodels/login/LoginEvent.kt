package com.dys.mobile.meucaminhao.viewmodels.login

sealed interface LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data object Access : LoginEvent
    data object AccessWithGoogle : LoginEvent
    data object ForgotPassword : LoginEvent
    data object Register : LoginEvent
}