package com.dys.mobile.meucaminhao.domain.dto

data class TripDTO(
    val id: Long,
    val title: String,
    val generalInformation: TripGeneralInfoDTO?,
    val income: TripIncomeDTO?,
    val expenses: TripExpensesDTO?,
    val checklist: List<TripChecklistDTO>?,
    val canEdit: Boolean?,
    val canDelete: Boolean?
)
