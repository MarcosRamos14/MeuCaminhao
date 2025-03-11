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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue40
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun BottomAppBarComponent() {
    val items = remember {
        listOf(
            Pair(R.string.text_management, R.drawable.ic_management),
            Pair(R.string.text_vehicles, R.drawable.ic_truck_outlined),
            Pair(R.string.text_home, R.drawable.ic_home),
            Pair(R.string.text_trips, R.drawable.ic_trips),
            Pair(R.string.text_more, R.drawable.ic_more)
        )
    }
    var selectedItem by remember {
        mutableStateOf(items.first())
    }

    Column {
        HorizontalDivider(color = Gray80, thickness = 1.dp)

        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            items.forEach { item ->
                val text = item.first
                val icon = item.second

                NavigationBarItem(
                    selected = selectedItem == item,
                    onClick = {
                        selectedItem = item
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = {
                        TextComponent(
                            text = stringResource(text),
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

        HorizontalDivider(color = Gray80, thickness = 1.dp)
    }
}

@Preview
@Composable
private fun BottomAppBarComponentPreview() {
    MeuCaminhaoTheme {
        BottomAppBarComponent()
    }
}