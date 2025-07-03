package com.dys.mobile.meucaminhao.domain.dto

data class ExpenseDTO(
    val title: String?,
    val amount: TotalAmountDTO?,
    val items: List<ExpenseItemDTO>?
)
