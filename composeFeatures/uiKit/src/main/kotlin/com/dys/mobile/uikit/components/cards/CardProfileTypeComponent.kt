package com.dys.mobile.uikit.components.cards

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.dys.mobile.uikit.components.enum.ProfileTypeInfoEnum
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray80
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes
import com.dys.mobile.uikit.theme.White

@Composable
fun CardProfileTypeComponent(
    @StringRes title: Int,
    @StringRes message: Int,
    modifier: Modifier = Modifier,
    profileTypeInfoEnum: ProfileTypeInfoEnum? = null,
    isExpandable: Boolean = true,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        border = if (isSelected) {
            BorderStroke(1._dpw, MaterialTheme.colorScheme.primary)
        } else {
            null
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16._dph),
            ) {
                Row {
                    val icon = if (isSelected) R.drawable.ic_check else R.drawable.ic_unchecked

                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(8._dpw))

                    TextComponent(
                        text = stringResource(title),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(8._dph))

                TextComponent(
                    text = stringResource(message),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (isSelected || !isExpandable) {
                profileTypeInfoEnum?.let { InvisibleContent(it) }
            }
        },
        onClick = onClick
    )
}

@Composable
fun InvisibleContent(
    profileTypeInfoEnum: ProfileTypeInfoEnum
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16._dpw, end = 16._dpw, bottom = 16._dph)
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 1000,
                    easing = LinearOutSlowInEasing
                )
            ),
        verticalArrangement = Arrangement.spacedBy(8._dph)
    ) {
        profileTypeInfoEnum.items.forEach { item ->
            CardIconAndTextComponent(
                icon = item.first,
                text = item.second
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardProfileTypeComponentPreview() {
    MeuCaminhaoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray80),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardProfileTypeComponent(
                title = R.string.text_owner,
                message = R.string.text_owner_profile_info,
                isSelected = true,
                profileTypeInfoEnum = ProfileTypeInfoEnum.ALPHA
            )
        }
    }
}