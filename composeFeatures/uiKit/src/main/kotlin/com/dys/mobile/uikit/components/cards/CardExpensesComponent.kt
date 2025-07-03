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
import com.dys.mobile.meucaminhao.domain.dto.ExpenseDTO
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
    tripExpenses: TripExpensesDTO?,
    openPhotoTicketClick: (String) -> Unit,
    openAdditionalPhotosClick: (String) -> Unit,
    deleteExpenseClick: () -> Unit,
    editExpenseClick: () -> Unit,
    newExpenseClick: () -> Unit
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
                        openPhotoTicketClick = openPhotoTicketClick,
                        openAdditionalPhotosClick = openAdditionalPhotosClick,
                        deleteExpenseClick = deleteExpenseClick,
                        editExpenseClick = editExpenseClick,
                    )

                    Spacer(modifier = Modifier.height(8._dph))
                }

                CardTotalValueComponent(
                    title = R.string.text_total_expense,
                    amount = tripExpenses?.amount
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
                    onClick = newExpenseClick
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
            amount = TotalAmountDTO(
                value = 934.74,
                currency = "BRL",
                formatted = "R$ 934,74",
                isPositive = true
            ),
            items = listOf(
                ExpenseDTO(
                    title = "Despesa recorrente",
                    amount = TotalAmountDTO(
                        value = 261.32,
                        currency = "BRL",
                        formatted = "R$ 261,32",
                        isPositive = true
                    ),
                    items = null
                )
            )
        )

        CardExpensesComponent(
            tripExpenses = tripExpenses,
            openPhotoTicketClick = { },
            openAdditionalPhotosClick = { },
            deleteExpenseClick = { },
            editExpenseClick = { },
            newExpenseClick = { }
        )
    }
}