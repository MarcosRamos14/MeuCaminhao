package com.dys.mobile.meucaminhao

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.dys.mobile.home.ui.HomeScreen
import com.dys.mobile.login.ui.LoginScreen
import com.dys.mobile.management.ui.ManagementScreen
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.onboarding.ui.newPassword.NewPasswordScreen
import com.dys.mobile.onboarding.ui.recoverPassword.RecoverPasswordScreen
import com.dys.mobile.onboarding.ui.register.CreateAccountScreen
import com.dys.mobile.onboarding.ui.register.PlanTypeScreen
import com.dys.mobile.onboarding.ui.register.ProfileTypeScreen
import com.dys.mobile.onboarding.ui.verifyCode.VerifyCodeScreen
import com.dys.mobile.trips.ui.TripDetailsScreen
import com.dys.mobile.trips.ui.TripsScreen
import com.dys.mobile.uikit.components.bottomBar.BottomAppBarComponent
import com.dys.mobile.vehicles.ui.VehiclesScreen

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
        Routes.TripsScreen.route
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
            startDestination = Routes.TripDetailsScreen.route // TODO:
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
                VehiclesScreen()
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
                route = Routes.TripsScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.TripsScreen.route}"
                    }
                )
            ) {
                TripsScreen(navController)
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
                route = Routes.TripDetailsScreen.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "${applicationId}://app/${Routes.TripDetailsScreen.route}"
                    }
                )
            ) {
                TripDetailsScreen()
            }
        }
    }
}