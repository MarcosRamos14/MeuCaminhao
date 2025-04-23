package com.dys.mobile.more.ui

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun MoreTooltip(
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            onClick = {
                //TODO:
            },
            text = {
                TextComponent(
                    text = "Ação 1",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
    }
}

@Preview
@Composable
private fun MoreTooltipPreview() {
    MeuCaminhaoTheme {
        MoreTooltip(
            expanded = true,
            onDismissRequest = {}
        )
    }
}