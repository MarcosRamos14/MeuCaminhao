package com.dys.mobile.meucaminhao.viewmodels.trips.history

import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.TripGeneralInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.TripIncomeDTO
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel

class TripsHistoryViewModel : BaseViewModel() {

    init {
        requestAllTrips()
    }

    private fun requestAllTrips() {
        launchWithState {
            //TODO: Request to API all trips
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

    fun getMockList(): List<TripDTO> {
        return listOf(
            TripDTO(
                id = 1,
                title = "Viagem para Rio de Janeiro",
                generalInformation = TripGeneralInfoDTO(
                    driver = "Marcos Ramos",
                    vehiclePlate = "AAA1BCD",
                    date = "Ago 23, 2025",
                    startTime = null,
                    duration = null,
                    manifestUrl = null,
                    course = null,
                    weight = null
                ),
                income = TripIncomeDTO(
                    totalAmount = TotalAmountDTO(
                        value = null,
                        currency = null,
                        formatted = "R$ 524,03",
                        isPositive = false
                    ),
                    balanceReceivable = null
                ),
                expenses = null,
                checklist = emptyList(),
                canDelete = null,
                canEdit = null,
            ),
            TripDTO(
                id = 2,
                title = "Viagem para Belo Horizonte",
                generalInformation = TripGeneralInfoDTO(
                    driver = "Marcos Ramos",
                    vehiclePlate = "AAA1BCD",
                    date = "Ago 25, 2025",
                    startTime = null,
                    duration = null,
                    manifestUrl = null,
                    course = null,
                    weight = null
                ),
                income = TripIncomeDTO(
                    totalAmount = TotalAmountDTO(
                        value = null,
                        currency = null,
                        formatted = "R$ 524,03",
                        isPositive = false
                    ),
                    balanceReceivable = null
                ),
                expenses = null,
                checklist = emptyList(),
                canDelete = null,
                canEdit = null,
            ),
            TripDTO(
                id = 1,
                title = "Viagem para Rio de Janeiro",
                generalInformation = TripGeneralInfoDTO(
                    driver = "Marcos Ramos",
                    vehiclePlate = "AAA1BCD",
                    date = "Ago 26, 2025",
                    startTime = null,
                    duration = null,
                    manifestUrl = null,
                    course = null,
                    weight = null
                ),
                income = TripIncomeDTO(
                    totalAmount = TotalAmountDTO(
                        value = null,
                        currency = null,
                        formatted = "R$ 524,03",
                        isPositive = false
                    ),
                    balanceReceivable = null
                ),
                expenses = null,
                checklist = emptyList(),
                canDelete = null,
                canEdit = null,
            )
        )
    }
}