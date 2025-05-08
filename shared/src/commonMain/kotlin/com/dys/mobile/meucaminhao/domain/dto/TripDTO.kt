package com.dys.mobile.meucaminhao.domain.dto

// TODO: Será alterado conforme retorno do back
data class TripDTO(
    val id: Long,
    val title: String,
    val origin: String, //TODO: Change type
    val destination: String, //TODO: Change type
    val startAt: String, //TODO: Change type
    val endAt: String?, //TODO: Change type
    val wight: Double,
    val manifestUrl: String?,
    val driver: String,
    val totalAmount: String, //TODO: Change type
    val remainingAmount: String?, //TODO: Change type
    val duration: String?,
    val status: String,
    val weightAmount: String?,//TODO: Change type
    val vehicle: TripVehicleDTO,
)
