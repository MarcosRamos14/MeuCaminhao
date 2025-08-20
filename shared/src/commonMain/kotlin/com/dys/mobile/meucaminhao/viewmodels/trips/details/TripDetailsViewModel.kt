package com.dys.mobile.meucaminhao.viewmodels.trips.details

import com.dys.mobile.meucaminhao.domain.dto.AdditionalResourceDTO
import com.dys.mobile.meucaminhao.domain.dto.ExpenseCategoryDTO
import com.dys.mobile.meucaminhao.domain.dto.ExpenseDTO
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemDTO
import com.dys.mobile.meucaminhao.domain.dto.ExpenseItemInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.ResourceDTO
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.TripChecklistDTO
import com.dys.mobile.meucaminhao.domain.dto.TripCourseDTO
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.TripExpensesDTO
import com.dys.mobile.meucaminhao.domain.dto.TripGeneralInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.TripIncomeDTO
import com.dys.mobile.meucaminhao.domain.dto.WeightDTO
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripDetailsViewModel : BaseViewModel() {

    private val _tripDetailsStateFlow = MutableStateFlow(TripDetailsState())
    val tripDetailsState = _tripDetailsStateFlow.asStateFlow()

    fun requestTripById(tripId: Long) {
        launchWithState {
            //TODO: Request to API trip by id
        }
    }

    fun onEvent(event: Event) {
        when (event) {
            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
        }
    }

    fun getMockList(): TripDTO {
        return TripDTO(
            id = 123,
            title = "Viagem do dia 12/04/2025",
            generalInformation = TripGeneralInfoDTO(
                driver = "Marcos Ramos",
                vehiclePlate = "AAA-1234",
                date = "Ago 23, 2025",
                startTime = "14:30",
                duration = "07:00:00",
                manifestUrl = "https://www.thecampusqdl.com/uploads/files/pdf_sample_2.pdf",
                course = TripCourseDTO(
                    origin = "Conselheiro Lafaiete MG",
                    destination = "Rio de Janeiro",
                    originLatitude = -20.655966,
                    originLongitude = -43.791516,
                    destinationLatitude = -22.871861,
                    destinationLongitude = -43.249196
                ),
                weight = WeightDTO(
                    value = 24.0,
                    unit = "t",
                    formatted = "24 toneladas"
                )
            ),
            income = TripIncomeDTO(
                totalAmount = TotalAmountDTO(
                    value = 12000.0,
                    currency = "BRL",
                    formatted = "R$ 12000,00",
                    isPositive = true
                ),
                balanceReceivable = TotalAmountDTO(
                    value = 2000.0,
                    currency = "BRL",
                    formatted = "R$ 2000,00",
                    isPositive = true
                )
            ),
            expenses = TripExpensesDTO(
                amount = TotalAmountDTO(
                    value = 1850.0,
                    currency = "",
                    isPositive = true,
                    formatted = "R$ 1850,00"
                ),
                items = listOf(
                    ExpenseDTO(
                        title = "Despesa recorrente",
                        amount = TotalAmountDTO(
                            value = 1200.0,
                            currency = "",
                            isPositive = true,
                            formatted = "R$ 1200,00"
                        ),
                        items = listOf(
                            ExpenseItemDTO(
                                expenseId = 1,
                                title = "Abastecimento",
                                vehiclePlate = "ABC-1234",
                                category = ExpenseCategoryDTO(
                                    categoryId = 1,
                                    name = "Combustível",
                                    type = "Recorrente"
                                ),
                                amount = TotalAmountDTO(
                                    value = 1000.0,
                                    currency = "",
                                    isPositive = true,
                                    formatted = "R$ 1000,00"
                                ),
                                generalInformation = ExpenseItemInfoDTO(
                                    driver = "Marcos Ramos",
                                    date = "Ago 23, 2025",
                                    amount = TotalAmountDTO(
                                        value = 1000.0,
                                        currency = "",
                                        isPositive = true,
                                        formatted = "R$ 1000,00"
                                    )
                                ),
                                ticketUrl = "https://picsum.photos/seed/picsum/200/300",
                                additionalResources = AdditionalResourceDTO(
                                    quantity = 2,
                                    resources = listOf(
                                        ResourceDTO(
                                            resourceId = 1,
                                            url = "https://picsum.photos/200/300?grayscale"
                                        ),
                                        ResourceDTO(
                                            resourceId = 1,
                                            url = "https://picsum.photos/200/300/?blur"
                                        )
                                    )
                                ),
                                observation = "Posto Cupim pago no cartão débito"
                            ),
                            ExpenseItemDTO(
                                expenseId = 2,
                                title = "Pedágio",
                                vehiclePlate = "ABC-1234",
                                category = ExpenseCategoryDTO(
                                    categoryId = 1,
                                    name = "Pedágio",
                                    type = "Recorrente"
                                ),
                                amount = TotalAmountDTO(
                                    value = 200.0,
                                    currency = "",
                                    isPositive = true,
                                    formatted = "R$ 200,00"
                                ),
                                generalInformation = ExpenseItemInfoDTO(
                                    driver = "Marcos Ramos",
                                    date = "Ago 23, 2025",
                                    amount = TotalAmountDTO(
                                        value = 200.0,
                                        currency = "",
                                        isPositive = true,
                                        formatted = "R$ 200,00"
                                    )
                                ),
                                ticketUrl = "https://picsum.photos/seed/picsum/200/300",
                                additionalResources = AdditionalResourceDTO(
                                    quantity = 2,
                                    resources = listOf(
                                        ResourceDTO(
                                            resourceId = 1,
                                            url = "https://picsum.photos/200/300?grayscale"
                                        ),
                                        ResourceDTO(
                                            resourceId = 1,
                                            url = "https://picsum.photos/200/300/?blur"
                                        )
                                    )
                                ),
                                observation = "Todos eixo no chão"
                            )
                        )
                    ),
                    ExpenseDTO(
                        title = "Despesa ocasional",
                        amount = TotalAmountDTO(
                            value = 650.0,
                            currency = "",
                            isPositive = true,
                            formatted = "R$ 650,00"
                        ),
                        items = listOf(
                            ExpenseItemDTO(
                                expenseId = 1,
                                title = "Manutenção do pneu",
                                vehiclePlate = "ABC-1234",
                                category = ExpenseCategoryDTO(
                                    categoryId = 1,
                                    name = "Manutenção",
                                    type = "Ocasional"
                                ),
                                amount = TotalAmountDTO(
                                    value = 650.0,
                                    currency = "",
                                    isPositive = true,
                                    formatted = "R$ 650,00"
                                ),
                                generalInformation = ExpenseItemInfoDTO(
                                    driver = "Marcos Ramos",
                                    date = "Ago 23, 2025",
                                    amount = TotalAmountDTO(
                                        value = 650.0,
                                        currency = "",
                                        isPositive = true,
                                        formatted = "R$ 650,00"
                                    )
                                ),
                                ticketUrl = "",
                                additionalResources = AdditionalResourceDTO(
                                    quantity = 2,
                                    resources = listOf(
                                        ResourceDTO(
                                            resourceId = 1,
                                            url = "https://picsum.photos/200/300?grayscale"
                                        ),
                                        ResourceDTO(
                                            resourceId = 1,
                                            url = "https://picsum.photos/200/300/?blur"
                                        )
                                    )
                                ),
                                observation = "Pago em dinheiro"
                            )
                        )
                    )
                )
            ),
            checklist = listOf(
                TripChecklistDTO(
                    checklistId = 1,
                    title = "Pré-jornada",
                    status = "COMPLETED"
                ),
                TripChecklistDTO(
                    checklistId = 1,
                    title = "Pós-jornada",
                    status = "INCOMPLETE"
                )
            ),
            canEdit = true,
            canDelete = true
        )
    }
}