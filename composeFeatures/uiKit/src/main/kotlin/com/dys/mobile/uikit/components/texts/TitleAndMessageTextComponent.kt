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
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun TextAndMessageTextComponent(
    @StringRes title: Int,
    message: String
) {
    Column {
        TextComponent(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(title),
            color = Gray65,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4._dph))

        TextComponent(
            modifier = Modifier.wrapContentWidth(),
            text = message,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun TextAndMessageTextComponentPreview() {
    MeuCaminhaoTheme {
        TextAndMessageTextComponent(
            R.string.text_driver,
            "Marcos Ramos"
        )
    }
}