package com.dys.mobile.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun FilledRoundButtonComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    icon: Int? = null,
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

@Composable
fun OutlinedRoundButtonComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    icon: Int? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                Spacer(modifier = Modifier.width(8.dp))
            }
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = text,
                color = Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun TextButtonComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    style: TextStyle,
    underline: Boolean = true,
    icon: Int? = null,
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = horizontalArrangement ?: Arrangement.Start,
        ) {
            icon?.let { drawableRes ->
                if (iconPosition) {
                    IconTexButtonComponent(drawableRes)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = text,
                color = MaterialTheme.colorScheme.primary,
                style = style.copy(
                    textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
                )
            )
            icon?.let { drawableRes ->
                if (!iconPosition) {
                    Spacer(modifier = Modifier.width(8.dp))
                    IconTexButtonComponent(drawableRes)
                }
            }
        }
    }
}

@Composable
private fun IconTexButtonComponent(drawableRes: Int) {
    Icon(
        painter = painterResource(id = drawableRes),
        contentDescription = null,
        modifier = Modifier.size(ButtonDefaults.IconSize),
        tint = Color.Unspecified
    )
}

@Preview
@Composable
private fun ButtonComponentPreview() {
    MeuCaminhaoTheme {

    }
}