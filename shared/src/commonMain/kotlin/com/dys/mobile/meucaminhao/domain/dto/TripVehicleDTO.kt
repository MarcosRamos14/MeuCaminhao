package com.dys.mobile.meucaminhao.domain.dto

// TODO: Será alterado conforme retorno do back
data class TripVehicleDTO(
    val id: Long,
    val plate: String,
    /**
     * brand + model
     */
    val model: String,
    val photoUrl: String?,
    val owner: String
)