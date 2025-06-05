package com.dys.mobile.uikit.components.appBar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    modifier: Modifier = Modifier.shadow(2.dp),
    title: String,
    hasDivider: Boolean = true
) {
    Column {
        TopAppBar(
            modifier = modifier,
            title = {
                TextComponent(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )

        if (hasDivider) HorizontalDivider(color = Gray80, thickness = 1.dp)
    }
}

@Preview
@Composable
private fun TopAppBarComponentPreview() {
    MeuCaminhaoTheme {
        TopAppBarComponent(
            modifier = Modifier,
            "Boas-vindas, Marcos!"
        )
    }
}