package com.dys.mobile.meucaminhao.domain.dto

data class TripExpensesDTO(
    val amount: TotalAmountDTO?,
    val items: List<ExpensiveDTO>?
)
