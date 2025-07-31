package com.dys.mobile.meucaminhao.domain.dto.vehicle

import com.dys.mobile.meucaminhao.domain.dto.ComponentDTO

data class VehicleDriverDTO(
    val id: Long,
    val name: String,
    val photoUrl: String?,
    val leftInfo: ComponentDTO?,
    val rightInfo: ComponentDTO?,
)