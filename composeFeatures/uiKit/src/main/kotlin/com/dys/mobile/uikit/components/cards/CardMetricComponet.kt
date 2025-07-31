package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.BlueSky
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardMetricComponent(
    modifier: Modifier = Modifier,
    value: String,
    overline: String,
    valueColor: Color = Black
) {
    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        content = {
            Column(
                modifier = Modifier.padding(horizontal = 16._dpw, vertical = 16._dph),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextComponent(
                    modifier = Modifier.wrapContentWidth(),
                    text = value,
                    color = valueColor,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )

                TextComponent(
                    modifier = Modifier.wrapContentWidth(),
                    text = overline,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardMetricPreview() {
    MeuCaminhaoTheme {
        CardMetricComponent(
            value = "471",
            overline = "Viagens",
            valueColor = BlueSky
        )
    }
}