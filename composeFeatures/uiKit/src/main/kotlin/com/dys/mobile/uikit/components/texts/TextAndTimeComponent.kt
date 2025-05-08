package com.dys.mobile.uikit.components.texts

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.theme.Gray65
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun TextAndTimeComponent(
    modifier: Modifier = Modifier,
    timeFontWeight: FontWeight = FontWeight.Normal,
    @DrawableRes icon: Int? = null,
    @StringRes title: Int? = null,
    time: String,
) {
    Column(modifier = modifier) {
        title?.let {
            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(it),
                color = Gray65,
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(4._dph))

        Card(
            shape = Shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = Gray80,
            ),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(horizontal = 16._dpw, vertical = 4._dph),
                        text = time,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = timeFontWeight,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    icon?.let { drawableRes ->
                        Icon(
                            painter = painterResource(id = drawableRes),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.width(4._dpw))
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun TextAndTimeComponentPreview() {
    MeuCaminhaoTheme {
        TextAndTimeComponent(
            title = R.string.text_date,
            time = "Ago 23, 2025"
        )
    }
}