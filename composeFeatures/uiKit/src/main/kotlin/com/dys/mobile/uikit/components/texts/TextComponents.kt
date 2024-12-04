package com.dys.mobile.uikit.components.texts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.dys.mobile.uikit.theme.Black

@Composable
fun TextComponent(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    color: Color = Black,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign? = TextAlign.Start,
    style: TextStyle
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign,
        style = style
    )
}