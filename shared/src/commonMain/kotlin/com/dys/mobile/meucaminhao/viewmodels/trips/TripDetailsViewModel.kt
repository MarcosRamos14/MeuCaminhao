package com.dys.mobile.meucaminhao.viewmodels.trips

import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemDTO
import com.dys.mobile.meucaminhao.domain.dto.ExpensiveDTO
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.TripChecklistDTO
import com.dys.mobile.meucaminhao.domain.dto.TripCourseDTO
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.TripExpensesDTO
import com.dys.mobile.meucaminhao.domain.dto.TripGeneralInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.TripIncomeDTO
import com.dys.mobile.meucaminhao.domain.dto.WeightDTO
import com.dys.mobile.meucaminhao.domain.enum.ChecklistStatusEnum
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel

class TripDetailsViewModel : BaseViewModel() {

    private fun requestTripById() {
        launchWithState {
            //TODO: Request to API trip by id
        }
    }

    fun getMockList(): TripDTO {
        return TripDTO(
            id = 123,
            title = "Viagem do dia 12/04/2025",
            generalInformation = TripGeneralInfoDTO(
                driver = "Marcos Moreira Ramos",
                vehiclePlate = "ABC-1234",
                date = "Abr 12, 2025",
                startTime = "09:41 PM",
                duration = "12:36:14",
                manifestUrl = "https://www.guiaviagensbrasil.com/imagens/Imagem%20do%20mar%20calma%20e%20belo%20da%20Praia%20da%20Engenhoca-Itacar%C3%A9-Bahia-BA.jpg",
                course = TripCourseDTO(
                    origin = "Av. do Contorno, 580, Belo Horizonte",
                    destination = "Av. Alm. Silvio de Noronha, 100, Rio de Janeiro",
                    originLatitude = -19.914595,
                    originLongitude = -43.928326,
                    destinationLatitude = -22.914365,
                    destinationLongitude = -43.167583
                ),
                weight = WeightDTO(
                    value = 1000.0,
                    unit = "t",
                    formatted = "1 tonelada"
                )
            ),
            income = TripIncomeDTO(
                totalAmount = TotalAmountDTO(
                    value = 3934.74,
                    currency = "BRL",
                    formatted = "R$ 3934,74",
                    isPositive = true
                ),
                balanceReceivable = TotalAmountDTO(
                    value = 934.74,
                    currency = "BRL",
                    formatted = "R$ 934,74",
                    isPositive = true
                )
            ),
            expenses = TripExpensesDTO(
                totalAmount = TotalAmountDTO(
                    value = 934.74,
                    currency = "BRL",
                    formatted = "R$ 934,74",
                    isPositive = true
                ),
                items = listOf(
                    ExpensiveDTO(
                        title = "Despesa recorrente",
                        amount = TotalAmountDTO(
                            value = 261.32,
                            currency = "BRL",
                            formatted = "R$ 261,32",
                            isPositive = true
                        ),
                        items = listOf(
                            ExpenseItemDTO(
                                expenseId = 123,
                                title = "Abastecimento",
                                vehiclePlate = "ABC-1234",
                                category = null,
                                amount = null,
                                generalInformation = null,
                                ticketUrl = "",
                                additionalResources = null,
                                observation = ""
                            ),
                            ExpenseItemDTO(
                                expenseId = 123,
                                title = "Abastecimento",
                                vehiclePlate = "ABC-1234",
                                category = null,
                                amount = null,
                                generalInformation = null,
                                ticketUrl = "",
                                additionalResources = null,
                                observation = ""
                            ),
                            ExpenseItemDTO(
                                expenseId = 123,
                                title = "Pedágio",
                                vehiclePlate = "ABC-1234",
                                category = null,
                                amount = null,
                                generalInformation = null,
                                ticketUrl = "",
                                additionalResources = null,
                                observation = ""
                            )
                        )
                    ),
                    ExpensiveDTO(
                        title = "Despesa ocasional",
                        amount = TotalAmountDTO(
                            value = 673.42,
                            currency = "BRL",
                            formatted = "R$ 673,42",
                            isPositive = true
                        ),
                        items = listOf(
                            ExpenseItemDTO(
                                expenseId = 123,
                                title = "Troca de pneu",
                                vehiclePlate = "ABC-1234",
                                category = null,
                                amount = null,
                                generalInformation = null,
                                ticketUrl = "",
                                additionalResources = null,
                                observation = ""
                            ),
                            ExpenseItemDTO(
                                expenseId = 123,
                                title = "Incremento de óleo",
                                vehiclePlate = "ABC-1234",
                                category = null,
                                amount = null,
                                generalInformation = null,
                                ticketUrl = "",
                                additionalResources = null,
                                observation = "Houve uma perda de óleo que necessitou fazer um complemento."
                            )
                        )
                    )
                )
            ),
            checklist = listOf(
                TripChecklistDTO(
                    checklistId = 12,
                    title = "Checklist de pré-jornada",
                    status = ChecklistStatusEnum.COMPLETED.name
                )
            ),
            canEdit = true,
            canDelete = true
        )
    }
}