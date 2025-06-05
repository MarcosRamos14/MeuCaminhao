package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.components.texts.TextComponent
import com.dys.mobile.uikit.theme.Gray50
import com.dys.mobile.uikit.theme.Gray85
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme

@Composable
fun CardExpenseItemComponent(
    modifier: Modifier = Modifier,
    containerColor: Color,
    item: ExpenseItemDTO
) {
    Card(
        modifier = modifier,
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        content = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16._dpw)
                    .clickable {
                        //TODO: Show BottomSheet
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButtonComponent(
                    modifier = Modifier,
                    text = item.title,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodySmall,
                    icon = R.drawable.ic_see_more,
                    iconColor = MaterialTheme.colorScheme.primary,
                    iconPosition = true,
                    contentPadding = PaddingValues(0._dph)
                )

                Spacer(modifier = Modifier.width(16._dpw))

                TextComponent(
                    modifier = Modifier.weight(1f),
                    text = item.amount?.formatted ?: stringResource(R.string.common_default_price),
                    textAlign = TextAlign.End,
                    color = Gray50,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardExpenseItemPreview() {
    MeuCaminhaoTheme {
        CardExpenseItemComponent(
            containerColor = Gray85,
            item = ExpenseItemDTO(
                expenseId = 123,
                title = "Abastecimento",
                vehiclePlate = "ABC-1234",
                category = null,
                amount = null,
                generalInformation = null,
                ticketUrl = "",
                additionalResources = null,
                observation = ""
            )
        )
    }
}