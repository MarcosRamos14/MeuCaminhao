package com.dys.mobile.uikit.components.texts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.Green80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Pink80

@Composable
fun TextAndMonetaryComponent(
    @StringRes title: Int,
    value: String,
    type: MonetaryValueType
) {
    val backgroundColor = when (type) {
        MonetaryValueType.POSITIVE -> Green80
        MonetaryValueType.NEGATIVE -> Pink80
        MonetaryValueType.NEUTRAL -> Blue80
    }

    Column {
        TextComponent(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(title),
            color = Gray65,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4._dph))

        // TODO: Finalizar implementação
    }
}

@Preview
@Composable
private fun TextAndMonetaryComponentPreview() {
    MeuCaminhaoTheme {
        TextAndMonetaryComponent(
            R.string.text_profit_loss,
            "R$ 420,00",
            MonetaryValueType.POSITIVE
        )
    }
}