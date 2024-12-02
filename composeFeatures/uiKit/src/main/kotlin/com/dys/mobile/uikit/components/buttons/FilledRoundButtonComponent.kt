package com.dys.mobile.uikit.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun FilledRoundButtonComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let { drawableRes ->
                Icon(
                    painter = painterResource(id = drawableRes),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun FilledRoundButtonComponentPreview() {
    MeuCaminhaoTheme {
        FilledRoundButtonComponent(
            text = "Entrar"
        )
    }
}