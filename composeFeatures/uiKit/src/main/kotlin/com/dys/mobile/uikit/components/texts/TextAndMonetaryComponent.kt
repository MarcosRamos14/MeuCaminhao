package com.dys.mobile.uikit.components.texts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.Green80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Orange80
import com.dys.mobile.uikit.theme.Pink80
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun TextAndMonetaryComponent(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    type: MonetaryValueType,
    value: String,
    valueColor: Color = Black,
) {
    val backgroundColor = when (type) {
        MonetaryValueType.POSITIVE -> Green80
        MonetaryValueType.NEGATIVE -> Pink80
        MonetaryValueType.NEUTRAL -> Blue80
        MonetaryValueType.WARNING -> Orange80
        MonetaryValueType.INDEFINITE -> Gray80
    }

    Column(modifier = modifier) {
        TextComponent(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(title),
            color = Gray65,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4._dph))

        Card(
            shape = Shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
            content = {
                TextComponent(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = 16._dpw, vertical = 4._dph),
                    text = value,
                    color = valueColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
    }
}

@Preview
@Composable
private fun TextAndMonetaryComponentPreview() {
    MeuCaminhaoTheme {
        TextAndMonetaryComponent(
            title = R.string.text_profit_loss,
            type = MonetaryValueType.POSITIVE,
            value = "R$ 420,00"
        )
    }
}