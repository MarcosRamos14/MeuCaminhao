package com.dys.mobile.login.ui

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)