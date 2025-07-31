package com.dys.mobile.meucaminhao.domain.dto.vehicle

data class VehicleChecklistDTO(
    val id: Long,
    val name: String,
    val lastReview: String?,
    val periodicity: String?,
)