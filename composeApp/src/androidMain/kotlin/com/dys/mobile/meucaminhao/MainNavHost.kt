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
import com.dys.mobile.onboarding.ui.recoverPassword.RecoverPasswordScreen

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
            RecoverPasswordScreen()
        }
    }
}