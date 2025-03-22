package com.dys.mobile.meucaminhao

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.dys.mobile.login.ui.LoginScreen
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.onboarding.ui.newPassword.NewPasswordScreen
import com.dys.mobile.onboarding.ui.recoverPassword.RecoverPasswordScreen
import com.dys.mobile.onboarding.ui.register.CreateAccountScreen
import com.dys.mobile.onboarding.ui.register.PlanTypeScreen
import com.dys.mobile.onboarding.ui.register.ProfileTypeScreen
import com.dys.mobile.onboarding.ui.verifyCode.VerifyCodeScreen

@Composable
fun MainNavHost() {
    val context = LocalContext.current
    val applicationId = remember { context.packageName }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route
    ) {
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
    }
}