package com.dys.mobile.toolkit.extensions

import androidx.navigation.NavController
import com.dys.mobile.meucaminhao.navigation.routes.popUpRoutes

fun NavController.handleRoute(route: String) {
    when {
        popUpRoutes.contains(route) -> {
            this.navigate(route) {
                popUpTo(this@handleRoute.graph.startDestinationId) { inclusive = true }
            }
        }

        else -> this.navigate(route)
    }
}