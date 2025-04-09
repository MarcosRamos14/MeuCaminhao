package com.dys.mobile.meucaminhao.viewmodels.login

import com.dys.mobile.meucaminhao.navigation.event.Event

sealed interface LoginEvent: Event {
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data object Access : LoginEvent
    data object AccessWithGoogle : LoginEvent
}