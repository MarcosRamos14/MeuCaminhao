package com.dys.mobile.meucaminhao.domain.dto

import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum

data class TripChecklistDTO(
    val checklistId: Long?,
    val title: String?,
    val status: String?
)

val TripChecklistDTO.checklistStatusEnum: ChecklistStatusEnum? get() = ChecklistStatusEnum.from(status)
