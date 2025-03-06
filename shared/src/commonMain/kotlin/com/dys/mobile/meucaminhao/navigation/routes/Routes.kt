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
    data object VerifyCodeScreen : Routes("verifyCodeScreen")
    data object NewPasswordScreen : Routes("newPasswordScreen")
    data object CreateAccountScreen : Routes("createAccountScreen")
    data object ProfileTypeScreen : Routes("profileTypeScreen")
    data object PlanTypeScreen : Routes("planTypeScreen")

    /**
     * Home flow routes
     */
    data object HomeScreen : Routes("homeScreen")
}