package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue70
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardPlateComponent(plateValue: String) {
    Card(
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Blue80,
        ),
        border = BorderStroke(1._dpw, Blue70),
        content = {
            TextComponent(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 16._dpw, vertical = 4._dph),
                text = plateValue,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}

@Preview
@Composable
private fun CardPlateComponentPreview() {
    MeuCaminhaoTheme {
        CardPlateComponent("IUX7H564")
    }
}