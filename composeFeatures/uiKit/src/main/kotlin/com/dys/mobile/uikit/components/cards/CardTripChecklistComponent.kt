package com.dys.mobile.uikit.components.cards

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardTripChecklistComponent(
    modifier: Modifier = Modifier,
    name: String,
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Row(
                modifier = Modifier.padding(horizontal = 16._dph),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(contentDescription),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(4._dpw))

                TextComponent(
                    modifier = Modifier.weight(1f),
                    text = name,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.width(16._dpw))

                TextButtonComponent(
                    modifier = Modifier,
                    text = stringResource(R.string.common_text_see_more),
                    style = MaterialTheme.typography.bodySmall,
                    icon = R.drawable.ic_see_more,
                    iconPosition = false,
                    contentPadding = PaddingValues(0._dph)
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardTripChecklistPreview() {
    MeuCaminhaoTheme {
        CardTripChecklistComponent(
            name = stringResource(R.string.text_complete_checklist),
            icon = R.drawable.ic_checked,
            contentDescription = R.string.text_complete_checklist,
            onClick = {}
        )
    }
}