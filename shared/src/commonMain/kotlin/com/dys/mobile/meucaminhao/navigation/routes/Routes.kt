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
    data object MyVehiclesScreen : Routes("myVehiclesScreen")
    data object VehicleDetailsScreen : Routes("vehicleDetailsScreen/{id}/{licensePlate}") {
        fun routeWithArgs(id: Long, licensePlate: String) = "vehicleDetailsScreen/$id/$licensePlate"
    }

    /**
     * Home flow route
     */
    data object HomeScreen : Routes("homeScreen")

    /**
     * Trips flow route
     */
    data object TripsHistoryScreen : Routes("tripsHistoryScreen")
    data object TripDetailsScreen : Routes("tripDetailsScreen/{tripId}") {
        fun routeWithArgs(tripId: Long) = "tripDetailsScreen/$tripId"
    }

    /**
     * More tooltip (special route, will not sail)
     */
    data object MoreTooltip : Routes("moreTooltip")

    /**
     * Shared
     */
    data object PhotoGalleryScreen : Routes("photoGalleryScreen/{url}") {
        fun routeWithArgs(url: String) = "photoGalleryScreen/$url"
    }
    data object FullPhotoScreen : Routes("fullPhotoScreen/{url}/{index}") {
        fun routeWithArgs(url: String, index: Int = 0) = "fullPhotoScreen/$url/$index"
    }

    companion object {
        const val ARG_TRIP_ID = "tripId"
        const val ARG_URL = "url"
        const val ARG_INDEX = "index"
        const val ARG_ID = "id"
        const val ARG_LICENSE_PLATE = "licensePlate"
    }
}