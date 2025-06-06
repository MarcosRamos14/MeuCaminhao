package com.dys.mobile.uikit.components.bottomBar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue40
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun BottomAppBarComponent(navController: NavController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val screens = remember {
        listOf(
            ScreenItem.ManagementScreen,
            ScreenItem.VehiclesScreen,
            ScreenItem.HomeScreen,
            ScreenItem.TripsScreen,
            ScreenItem.MoreTooltip
        )
    }

    Column {
        HorizontalDivider(color = Gray80, thickness = 1.dp)

        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            screens.forEach { screen ->
                with(screen.bottomAppBarItem) {
                    NavigationBarItem(
                        selected = (currentDestination == route),
                        onClick = {
                            navController.navigate(route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = iconResId),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        label = {
                            TextComponent(
                                text = stringResource(labelResId),
                                color = Color.Unspecified,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.labelMedium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Blue40,
                            selectedTextColor = Blue40,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }

        HorizontalDivider(color = Gray80, thickness = 1.dp)
    }
}

@Preview
@Composable
private fun BottomAppBarComponentPreview() {
    MeuCaminhaoTheme {
    }
}