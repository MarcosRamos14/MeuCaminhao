package com.dys.mobile.meucaminhao.domain.dto

data class TripCourseDTO(
    val origin: String?,
    val destination: String?,
    val originLatitude: Double?,
    val originLongitude: Double?,
    val destinationLatitude: Double?,
    val destinationLongitude: Double?
)
