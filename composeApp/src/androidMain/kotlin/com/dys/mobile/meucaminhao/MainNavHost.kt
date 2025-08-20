package com.dys.mobile.meucaminhao

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.dys.mobile.home.ui.HomeScreen
import com.dys.mobile.login.ui.LoginScreen
import com.dys.mobile.management.ui.ManagementScreen
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.navigation.routes.Routes.Companion.ARG_INDEX
import com.dys.mobile.meucaminhao.navigation.routes.Routes.Companion.ARG_TRIP_ID
import com.dys.mobile.meucaminhao.navigation.routes.Routes.Companion.ARG_URL
import com.dys.mobile.meucaminhao.navigation.routes.Routes.Companion.ARG_ID
import com.dys.mobile.meucaminhao.navigation.routes.Routes.Companion.ARG_LICENSE_PLATE
import com.dys.mobile.onboarding.ui.newPassword.NewPasswordScreen
import com.dys.mobile.onboarding.ui.recoverPassword.RecoverPasswordScreen
import com.dys.mobile.onboarding.ui.register.CreateAccountScreen
import com.dys.mobile.onboarding.ui.register.PlanTypeScreen
import com.dys.mobile.onboarding.ui.register.ProfileTypeScreen
import com.dys.mobile.onboarding.ui.verifyCode.VerifyCodeScreen
import com.dys.mobile.trips.ui.tripDetails.TripDetailsScreen
import com.dys.mobile.trips.ui.tripsHistory.TripsHistoryScreen
import com.dys.mobile.uikit.components.bottomBar.BottomAppBarComponent
import com.dys.mobile.uikit.screens.photos.FullPhotoScreen
import com.dys.mobile.uikit.screens.photos.PhotoGalleryScreen
import com.dys.mobile.vehicles.ui.editVehicle.EditVehicleScreen
import com.dys.mobile.vehicles.ui.myVehicles.MyVehiclesScreen
import com.dys.mobile.vehicles.ui.newVehicle.NewVehicleScreen
import com.dys.mobile.vehicles.ui.vehicleDetails.VehicleDetailsScreen
import com.dys.mobile.vehicles.ui.vehicles.VehiclesScreen

@Composable
fun MainNavHost() {
    val context = LocalContext.current
    val applicationId = remember { context.packageName }
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val screensWithBars = listOf(
        Routes.ManagementScreen.route,
        Routes.VehiclesScreen.route,
        Routes.HomeScreen.route,
        Routes.TripsHistoryScreen.route
    )

    val shouldShowBars = (currentDestination in screensWithBars)

    Scaffold(
        bottomBar = {
            if (shouldShowBars) BottomAppBarComponent(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Routes.VehiclesScreen.route // TODO:
        ) {
            composable(
                route = Routes.ManagementScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.ManagementScreen.route}"
                    }
                )
            ) {
                ManagementScreen()
            }

            composable(
                route = Routes.VehiclesScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.VehiclesScreen.route}"
                    }
                )
            ) {
                VehiclesScreen(navController)
            }

            composable(
                route = Routes.NewVehicleScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.NewVehicleScreen.route}"
                    }
                )
            ) {
                NewVehicleScreen(navController)
            }

            composable(
                route = Routes.MyVehiclesScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.MyVehiclesScreen.route}"
                    }
                )
            ) {
                MyVehiclesScreen(navController)
            }

            composable(
                route = Routes.VehicleDetailsScreen.route,
                arguments = listOf(
                    navArgument(ARG_ID) { type = NavType.LongType },
                    navArgument(ARG_LICENSE_PLATE) { type = NavType.StringType }
                ),
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.VehicleDetailsScreen.route}/${ARG_ID}/${ARG_LICENSE_PLATE}"
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong(ARG_ID) ?: -1L
                val licensePlate = backStackEntry.arguments?.getString(ARG_LICENSE_PLATE) ?: ""

                VehicleDetailsScreen(
                    navController = navController,
                    id = id,
                    licensePlate = licensePlate
                )
            }

            composable(
                route = Routes.EditVehicleScreen.route,
                arguments = listOf(
                    navArgument(ARG_ID) { type = NavType.LongType },
                ),
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.EditVehicleScreen.route}/${ARG_ID}"
                    }
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong(ARG_ID) ?: -1L

                EditVehicleScreen(id = id)
            }

            composable(
                route = Routes.HomeScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.HomeScreen.route}"
                    }
                )
            ) {
                HomeScreen(navController)
            }

            composable(
                route = Routes.TripsHistoryScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.TripsHistoryScreen.route}"
                    }
                )
            ) {
                TripsHistoryScreen(navController)
            }

            composable(
                route = Routes.TripDetailsScreen.route,
                arguments = listOf(
                    navArgument(ARG_TRIP_ID) { type = NavType.LongType }
                ),
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.TripDetailsScreen.route}/${ARG_TRIP_ID}"
                    }
                )
            ) { backStackEntry ->
                val tripId = backStackEntry.arguments?.getLong(ARG_TRIP_ID) ?: -1L
                TripDetailsScreen(tripId = tripId, navController)
            }

            composable(
                route = Routes.LoginScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.LoginScreen.route}"
                    }
                )
            ) {
                LoginScreen(navController)
            }

            composable(
                route = Routes.RecoverPasswordScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.RecoverPasswordScreen.route}"
                    }
                )
            ) {
                RecoverPasswordScreen(navController)
            }

            composable(
                route = Routes.VerifyCodeScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.VerifyCodeScreen.route}"
                    }
                )
            ) {
                VerifyCodeScreen(navController)
            }

            composable(
                route = Routes.NewPasswordScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.NewPasswordScreen.route}"
                    }
                )
            ) {
                NewPasswordScreen(navController)
            }

            composable(
                route = Routes.CreateAccountScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.CreateAccountScreen.route}"
                    }
                )
            ) {
                CreateAccountScreen(navController)
            }

            composable(
                route = Routes.ProfileTypeScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.ProfileTypeScreen.route}"
                    }
                )
            ) {
                ProfileTypeScreen(navController)
            }

            composable(
                route = Routes.PlanTypeScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.PlanTypeScreen.route}"
                    }
                )
            ) {
                PlanTypeScreen()
            }

            composable(
                route = Routes.PhotoGalleryScreen.route,
                arguments = listOf(
                    navArgument(ARG_URL) { type = NavType.StringType }
                ),
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.PhotoGalleryScreen.route}/${ARG_URL}"
                    }
                )
            ) { backStackEntry ->
                val encodedUrlList = backStackEntry.arguments?.getString(ARG_URL)
                val urlList = encodedUrlList
                    ?.split(",")
                    ?.map { Uri.decode(it) }
                    ?: emptyList()

                PhotoGalleryScreen(
                    navController = navController,
                    photos = urlList
                )
            }

            composable(
                route = Routes.FullPhotoScreen.route,
                arguments = listOf(
                    navArgument(ARG_URL) { type = NavType.StringType },
                    navArgument(ARG_INDEX) { type = NavType.IntType }
                ),
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.FullPhotoScreen.route}/${ARG_URL}/${ARG_INDEX}"
                    }
                )
            ) { backStackEntry ->
                val encodedUrlList = backStackEntry.arguments?.getString(ARG_URL)
                val index = backStackEntry.arguments?.getInt(ARG_INDEX) ?: 0
                val urlList = encodedUrlList
                    ?.split(",")
                    ?.map { Uri.decode(it) }
                    ?: emptyList()

                FullPhotoScreen(
                    photos = urlList,
                    initialIndex = index
                )
            }
        }
    }
}