package com.dys.mobile.meucaminhao.domain.dto

data class TripGeneralInfoDTO(
    val driver: String?,
    val vehiclePlate: String?,
    val date: String?,
    val startTime: String?,
    val duration: String?,
    val manifestUrl: String?,
    val course: TripCourseDTO?,
    val weight: WeightDTO?
)
