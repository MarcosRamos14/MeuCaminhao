package com.dys.mobile.meucaminhao.viewmodels.login

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val email: String = "",
    val password: String = "",
    val credentialsError: CredentialsErrorState? = null
)

fun MutableStateFlow<LoginState>.updateEmail(email: String) {
    update { it.copy(email = email) }
}

fun MutableStateFlow<LoginState>.updatePassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<LoginState>.updateCredentialsError(error: CredentialsErrorState?) {
    update { it.copy(credentialsError = error) }
}

data class EmailAndPassword(val email: String, val pwd: String)
val MutableStateFlow<LoginState>.emailAndPassword: EmailAndPassword
    get() = EmailAndPassword(value.email, value.password)