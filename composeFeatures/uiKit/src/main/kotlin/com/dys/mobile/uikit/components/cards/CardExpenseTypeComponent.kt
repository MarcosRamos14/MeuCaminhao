package com.dys.mobile.uikit.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Blue80
import com.dys.mobile.uikit.theme.Gray85
import com.dys.mobile.uikit.theme.Gray90
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardExpenseTypeComponent(
    modifier: Modifier = Modifier,
    title: String,
    amount: String?,
    items: List<ExpenseItemDTO>,
    openPhotoTicketClick: (String) -> Unit,
    openAdditionalPhotosClick: (String) -> Unit,
    deleteExpenseClick: () -> Unit,
    editExpenseClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "IconRotation"
    )

    Card(
        modifier = modifier,
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Blue80
        ),
        content = {
            Column {
                Row(
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(vertical = 16._dph, horizontal = 16._dpw),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextComponent(
                        modifier = Modifier.weight(1f),
                        text = title,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextComponent(
                        modifier = Modifier.wrapContentWidth(),
                        text = amount ?: stringResource(R.string.common_default_price),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.width(8._dpw))

                    Icon(
                        modifier = Modifier.rotate(rotationAngle),
                        painter = painterResource(R.drawable.ic_down_arrow),
                        contentDescription = stringResource(R.string.common_text_see_more),
                        tint = Color.Unspecified
                    )
                }

                AnimatedVisibility(
                    visible = expanded,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    Column {
                        val colors = listOf(Gray90, Gray85)

                        items.forEachIndexed { index, item ->
                            val backgroundColor = colors[index % colors.size]
                            CardExpenseItemComponent(
                                containerColor = backgroundColor,
                                item = item,
                                openPhotoTicketClick = openPhotoTicketClick,
                                openAdditionalPhotosClick = openAdditionalPhotosClick,
                                deleteExpenseClick = deleteExpenseClick,
                                editExpenseClick = editExpenseClick,
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardExpenseTypePreview() {
    MeuCaminhaoTheme {
        CardExpenseTypeComponent(
            title = "Despesa recorrente",
            amount = "R$ 261,32",
            items = emptyList(),
            openPhotoTicketClick = { },
            openAdditionalPhotosClick = { },
            deleteExpenseClick = { },
            editExpenseClick = { }
        )
    }
}