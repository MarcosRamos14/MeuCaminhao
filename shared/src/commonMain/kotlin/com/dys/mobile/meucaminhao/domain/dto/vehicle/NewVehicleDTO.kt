package com.dys.mobile.meucaminhao.domain.dto.vehicle

data class NewVehicleDTO(
    val plate: String,
    val brand: String,
    val model: String,
    val photoUri: String?,
    val drivers: List<DriverToLinkDTO>?,
    val checklists: List<ChecklistToLinkDTO>?,
)