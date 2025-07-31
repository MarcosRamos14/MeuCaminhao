package com.dys.mobile.uikit.components.cards

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardExpandableCountComponent(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    listSize: Int,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    content: @Composable () -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "IconRotation"
    )

    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Column {
                Row(
                    modifier = Modifier
                        .clickable { onExpandToggle() }
                        .padding(vertical = 16._dph, horizontal = 16._dpw),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(
                        modifier = Modifier.wrapContentWidth(),
                        text = listSize.toString(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    TextComponent(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8._dpw),
                        text = stringResource(title),
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )

                    Icon(
                        modifier = Modifier.rotate(rotationAngle),
                        painter = painterResource(R.drawable.ic_down_arrow),
                        contentDescription = stringResource(R.string.common_text_see_more),
                        tint = Color.Unspecified
                    )
                }

                AnimatedVisibility(
                    modifier = Modifier.padding(horizontal = 16._dpw),
                    visible = isExpanded,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    content()
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardExpandableCountPreview() {
    MeuCaminhaoTheme {
        CardExpandableCountComponent(
            title = R.string.text_driver,
            listSize = 3,
            isExpanded = true,
            onExpandToggle = { },
            content = { }
        )
    }
}