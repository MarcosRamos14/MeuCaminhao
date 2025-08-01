package com.dys.mobile.meucaminhao.domain.dto.vehicle

import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO

data class VehicleDTO(
    val id: Long,
    val plate: String,
    val createdAt: String?,
    val totalIncome: TotalAmountDTO?,
    val totalExpense: TotalAmountDTO?,
)