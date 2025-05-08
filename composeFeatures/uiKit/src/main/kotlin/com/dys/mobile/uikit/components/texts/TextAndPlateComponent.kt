package com.dys.mobile.uikit.components.texts

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
import com.dys.mobile.uikit.components.cards.CardPlateComponent
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun TextAndPlateComponent(
    modifier: Modifier = Modifier,
    plateValue: String
) {
    Column(modifier = modifier) {
        TextComponent(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.text_plate),
            color = Gray65,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4._dph))

        CardPlateComponent(plateValue)
    }
}

@Preview
@Composable
private fun TextAndPlateComponentPreview() {
    MeuCaminhaoTheme {
        TextAndPlateComponent(
            plateValue = "IUX7H564"
        )
    }
}