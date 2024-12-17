package com.dys.mobile.meucaminhao.navigation.routes

sealed class Routes(val route: String) {

    /**
     * Login flow routes
     */
    data object LoginScreen : Routes("loginScreen")

    /**
     * Onboarding flow routes
     */
    data object RecoverPasswordScreen : Routes("recoverPasswordScreen")
}