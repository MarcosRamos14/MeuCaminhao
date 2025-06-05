package com.dys.mobile.meucaminhao.domain.dto

data class TripExpensesDTO(
    val totalAmount: TotalAmountDTO?,
    val items: List<ExpensiveDTO>?
)
