package com.dys.mobile.meucaminhao.navigation.routes

sealed class Routes(val route: String) {
    /**
     * Login flow route
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
     * Management flow route
     */
    data object ManagementScreen : Routes("managementScreen")

    /**
     * Vehicles flow route
     */
    data object VehiclesScreen : Routes("vehiclesScreen")

    /**
     * Home flow route
     */
    data object HomeScreen : Routes("homeScreen")

    /**
     * Trips flow route
     */
    data object TripsScreen : Routes("tripsScreen")
    data object TripDetailsScreen : Routes("tripDetailsScreen")

    /**
     * More tooltip (special route, will not sail)
     */
    data object MoreTooltip : Routes("moreTooltip")
}