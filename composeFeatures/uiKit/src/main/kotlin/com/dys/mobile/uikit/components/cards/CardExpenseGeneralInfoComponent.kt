package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.enum.MonetaryValueType
import com.dys.mobile.uikit.components.texts.TextAndMessageTextComponent
import com.dys.mobile.uikit.components.texts.TextAndMonetaryComponent
import com.dys.mobile.uikit.components.texts.TextAndTimeComponent
import com.dys.mobile.uikit.theme.Gray90
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardExpenseGeneralInfoComponent(
    modifier: Modifier = Modifier,
    categoryType: String?,
    expenseItemInfo: ExpenseItemInfoDTO?,
) {
    Card(
        modifier = modifier.border(1.dp, Gray90, shape = Shapes.large),
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16._dph),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMessageTextComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.common_responsible,
                        message = expenseItemInfo?.driver ?: stringResource(R.string.common_error_getting_name),
                        messageMaxLin = 1
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndTimeComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.text_date,
                        time = expenseItemInfo?.date ?: stringResource(R.string.common_date_not_defined)
                    )
                }

                Spacer(modifier = Modifier.height(16._dph))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.common_value,
                        type = MonetaryValueType.NEGATIVE,
                        value = expenseItemInfo?.amount?.formatted ?: stringResource(R.string.common_default_price)
                    )

                    Spacer(modifier = Modifier.width(16._dpw))

                    TextAndMonetaryComponent(
                        modifier = Modifier.weight(1f),
                        title = R.string.common_type,
                        type = MonetaryValueType.INDEFINITE,
                        value = categoryType ?: stringResource(R.string.common_error_in_type),
                        valueColor = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CardExpenseGeneralInfoPreview() {
    MeuCaminhaoTheme {
        CardExpenseGeneralInfoComponent(
            expenseItemInfo = ExpenseItemInfoDTO(
                driver = "Marcos Moreira Ramos",
                date = "Ago 25, 2023",
                amount = TotalAmountDTO(
                    value = 5421.57,
                    currency = null,
                    formatted = "R$ 5421,57",
                    isPositive = null
                )
            ),
            categoryType = "Recorrente"
        )
    }
}