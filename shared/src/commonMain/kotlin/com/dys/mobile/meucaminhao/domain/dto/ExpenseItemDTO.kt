package com.dys.mobile.meucaminhao.domain.dto

data class ExpenseItemDTO(
    val expenseId: Int,
    val title: String,
    val vehiclePlate: String?,
    val category: ExpenseCategoryDTO?,
    val amount: TotalAmountDTO?,
    val generalInformation: ExpenseItemInfoDTO?,
    val ticketUrl: String?,
    val additionalResources: AdditionalResourceDTO?,
    val observation: String?
)
