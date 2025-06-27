package com.dys.mobile.uikit.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun OutlinedRoundButtonComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    modifierRow: Modifier = Modifier.fillMaxWidth(),
    text: String,
    fontWeight: FontWeight = FontWeight.SemiBold,
    color: Color? = null,
    @DrawableRes icon: Int? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding,
        border = BorderStroke(1._dpw, color ?: Color.Gray)
    ) {
        Row(
            modifier = modifierRow,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let { drawableRes ->
                Icon(
                    painter = painterResource(id = drawableRes),
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8._dpw))
            }
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = text,
                color = color ?: Black,
                fontWeight = fontWeight,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun OutlinedRoundButtonComponentPreview() {
    MeuCaminhaoTheme {
        OutlinedRoundButtonComponent(
            text = "Entrar"
        )
    }
}