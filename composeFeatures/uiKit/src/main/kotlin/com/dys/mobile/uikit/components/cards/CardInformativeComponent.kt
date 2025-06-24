package com.dys.mobile.uikit.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.components.images.ImageComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardInformativeComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    url: String? = null,
    @StringRes contentDescription: Int? = null,
    openImageClick: () -> Unit = { }
) {
    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = Blue80
        ),
        content = {
            Column(
                modifier = Modifier.padding(vertical = 16._dph, horizontal = 16._dpw),
                verticalArrangement = Arrangement.spacedBy(8._dph)
            ) {
                title?.let {
                    TextComponent(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                message?.let {
                    TextComponent(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                url?.let {
                    ImageComponent(
                        url = it,
                        contentDescription = contentDescription ?: 0,
                        openImageClick = openImageClick
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardInformativePreview() {
    MeuCaminhaoTheme {
        CardInformativeComponent(
            title = "Despesa com pagamento de pedágio."
        )
    }
}