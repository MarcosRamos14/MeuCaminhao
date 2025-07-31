package com.dys.mobile.meucaminhao.domain.dto.vehicle

import com.dys.mobile.meucaminhao.domain.dto.ComponentDTO

data class VehicleInfoDTO(
    val id: Long,
    val plate: String,
    val brand: String?,
    val model: String?,
    val createdAt: String,
    val photoUrl: String?,
    val history: List<ComponentDTO>?,
    val drivers: List<VehicleDriverDTO>?,
    val checklists: List<VehicleChecklistDTO>?
)