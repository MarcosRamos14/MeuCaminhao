package com.dys.mobile.uikit.components.cards

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Black
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Pink80
import com.dys.mobile.uikit.theme.Red60
import com.dys.mobile.uikit.theme.Shapes
import com.dys.mobile.uikit.theme.White

@Composable
fun CardTotalValueComponent(
    modifier: Modifier = Modifier,
    isExpense: Boolean = true,
    @StringRes title: Int,
    amount: TotalAmountDTO?,
) {
    val containerColor = when {
        isExpense -> Pink80
        amount?.isPositive == false -> Pink80
        else -> White
    }
    val textValueColor = when {
        isExpense -> Red60
        amount?.isPositive == false -> Red60
        else -> Black
    }

    Card(
        modifier = modifier,
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(containerColor = containerColor),
        content = {
            Row(
                modifier = Modifier.padding(vertical = 16._dph, horizontal = 16._dpw),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextComponent(
                    modifier = Modifier.weight(1f),
                    text = stringResource(title),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.width(16._dpw))

                TextComponent(
                    modifier = Modifier.wrapContentWidth(),
                    text = amount?.formatted ?: stringResource(R.string.common_default_price),
                    color = textValueColor,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardTotalValuePreview() {
    MeuCaminhaoTheme {
        CardTotalValueComponent(
            title = R.string.text_total_expense,
            amount = TotalAmountDTO(
                value = 934.74,
                currency = "BRL",
                formatted = null,
                isPositive = null
            ),
            isExpense = false
        )
    }
}