package com.dys.mobile.uikit.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemDTO
import com.dys.mobile.meucaminhao.domain.dto.ExpensiveDTO
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.TripExpensesDTO
import com.dys.mobile.toolkit.extensions._dph
import com.dys.mobile.toolkit.extensions._dpw
import com.dys.mobile.uikit.R
import com.dys.mobile.uikit.components.buttons.TextButtonComponent
import com.dys.mobile.uikit.theme.MeuCaminhaoTheme
import com.dys.mobile.uikit.theme.Shapes

@Composable
fun CardExpensesComponent(
    modifier: Modifier = Modifier,
    tripExpenses: TripExpensesDTO?
) {
    Card(
        modifier = modifier,
        shape = Shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        content = {
            Column(
                modifier = Modifier.padding(top = 16._dph, start = 16._dpw, end = 16._dpw),
            ) {
                tripExpenses?.items?.map { expense ->
                    CardExpenseTypeComponent(
                        title = expense.title ?: "",
                        amount = expense.amount?.formatted,
                        items = expense.items ?: emptyList(),
                    )

                    Spacer(modifier = Modifier.height(8._dph))
                }

                CardTotalValueComponent(
                    title = R.string.text_total_expense,
                    amount = tripExpenses?.totalAmount
                )

                TextButtonComponent(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.End),
                    text = stringResource(R.string.text_new_expense),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    icon = R.drawable.ic_add,
                    iconColor = MaterialTheme.colorScheme.primary,
                    iconPosition = true,
                    contentPadding = PaddingValues(0._dph),
                    onClick = {
                        // TODO: Mostrar tela para add uma nova despesa
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardExpensesPreview() {
    MeuCaminhaoTheme {
        val tripExpenses = TripExpensesDTO(
            totalAmount = TotalAmountDTO(
                value = 934.74,
                currency = "BRL",
                formatted = "R$ 934,74",
                isPositive = true
            ),
            items = listOf(
                ExpensiveDTO(
                    title = "Despesa recorrente",
                    amount = TotalAmountDTO(
                        value = 261.32,
                        currency = "BRL",
                        formatted = "R$ 261,32",
                        isPositive = true
                    ),
                    items = null
                ),
                ExpensiveDTO(
                    title = "Despesa ocasional",
                    amount = TotalAmountDTO(
                        value = 673.42,
                        currency = "BRL",
                        formatted = "R$ 673,42",
                        isPositive = true
                    ),
                    items = listOf(
                        ExpenseItemDTO(
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
                )
            )
        )

        CardExpensesComponent(
            tripExpenses = tripExpenses
        )
    }
}