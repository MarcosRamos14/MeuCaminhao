package com.dys.mobile.uikit.components.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.uikit.R

sealed class ScreenItem(
    val bottomAppBarItem: BottomAppBarItem
) {
    data object ManagementScreen : ScreenItem(
        bottomAppBarItem = BottomAppBarItem(
            route = Routes.ManagementScreen.route,
            iconResId = R.drawable.ic_management,
            labelResId = R.string.text_management
        )
    )

    data object VehiclesScreen : ScreenItem(
        bottomAppBarItem = BottomAppBarItem(
            route = Routes.VehiclesScreen.route,
            iconResId = R.drawable.ic_truck_outlined,
            labelResId = R.string.text_vehicles
        )
    )

    data object HomeScreen : ScreenItem(
        bottomAppBarItem = BottomAppBarItem(
            route = Routes.HomeScreen.route,
            iconResId = R.drawable.ic_home,
            labelResId = R.string.text_home
        )
    )

    data object TripsScreen : ScreenItem(
        bottomAppBarItem = BottomAppBarItem(
            route = Routes.TripsScreen.route,
            iconResId = R.drawable.ic_trips,
            labelResId = R.string.text_trips
        )
    )

    data object MoreTooltip : ScreenItem(
        bottomAppBarItem = BottomAppBarItem(
            route = Routes.MoreTooltip.route,
            iconResId = R.drawable.ic_more,
            labelResId = R.string.text_more
        )
    )
}

data class BottomAppBarItem(
    val route: String,
    @DrawableRes val iconResId: Int,
    @StringRes val labelResId: Int,
)