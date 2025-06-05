package com.dys.mobile.uikit.components.buttons

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle,
    underline: Boolean = true,
    @DrawableRes icon: Int? = null,
    iconColor: Color = Color.Unspecified,
    iconPosition: Boolean = true,
    horizontalArrangement: Arrangement.Horizontal? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = horizontalArrangement ?: Arrangement.Start,
        ) {
            icon?.let { drawableRes ->
                if (iconPosition) {
                    IconTexButtonComponent(drawableRes, iconColor)
                    Spacer(modifier = Modifier.width(8._dpw))
                }
            }
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = text,
                fontWeight = fontWeight,
                color = MaterialTheme.colorScheme.primary,
                style = style.copy(
                    textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
                )
            )
            icon?.let { drawableRes ->
                if (!iconPosition) {
                    Spacer(modifier = Modifier.width(8._dpw))
                    IconTexButtonComponent(drawableRes, iconColor)
                }
            }
        }
    }
}

@Composable
private fun IconTexButtonComponent(drawableRes: Int, iconColor: Color) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = Modifier.size(ButtonDefaults.IconSize),
        tint = iconColor
    )
}

@Preview
@Composable
private fun TextButtonComponentPreview() {
    MeuCaminhaoTheme {
        TextButtonComponent(
            text = "Entrar",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}