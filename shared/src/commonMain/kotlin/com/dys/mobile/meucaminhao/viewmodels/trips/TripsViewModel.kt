package com.dys.mobile.meucaminhao.viewmodels.trips

import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.TripVehicleDTO
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel

class TripsViewModel : BaseViewModel() {

    init {
        requestAllTrips()
    }

    private fun requestAllTrips() {
        launchWithState {
            //TODO: Request to API all trips
        }
    }

    fun getMockList(): List<TripDTO> {
        return listOf(
            TripDTO(
                id = 1,
                title = "Viagem para Rio de Janeiro",
                origin = "Belo Horizonte",
                destination = "Rio de Janeiro",
                startAt = "Ago 23, 2025",
                endAt = null,
                wight = 15000.0,
                manifestUrl = null,
                driver = "Marcos Moreira Ramos",
                totalAmount = "R$2500,00",
                remainingAmount = "",
                duration = "12:50:00",
                status = "DONE",
                weightAmount = "R$125,50",
                vehicle = TripVehicleDTO(
                    id = 1,
                    plate = "QUB6J82",
                    model = "Volvo 540",
                    photoUrl = null,
                    owner = "Marcos Moreira Ramos"
                )
            ),
            TripDTO(
                id = 2,
                title = "Viagem para São Paulo",
                origin = "Belo Horizonte",
                destination = "São Paulo",
                startAt = "Ago 14, 2025",
                endAt = "Ago 15, 2025",
                wight = 13000.0,
                manifestUrl = null,
                driver = "Yuri Barbosa",
                totalAmount = "R$2500,00",
                remainingAmount = "",
                duration = "15:00:00",
                status = "IN_PROGRESS",
                weightAmount = "R$125,50",
                vehicle = TripVehicleDTO(
                    id = 1,
                    plate = "IUX7H564",
                    model = "Scania R420",
                    photoUrl = null,
                    owner = "Marcos Moreira Ramos"
                )
            ),
            TripDTO(
                id = 3,
                title = "Viagem para São Paulo",
                origin = "Belo Horizonte",
                destination = "São Paulo",
                startAt = "Ago 14, 2025",
                endAt = "Ago 15, 2025",
                wight = 13000.0,
                manifestUrl = null,
                driver = "Yuri Barbosa",
                totalAmount = "R$2500,00",
                remainingAmount = "",
                duration = "15:00:00",
                status = "IN_PROGRESS",
                weightAmount = "R$125,50",
                vehicle = TripVehicleDTO(
                    id = 1,
                    plate = "IUX7H564",
                    model = "Scania R420",
                    photoUrl = null,
                    owner = "Marcos Moreira Ramos"
                )
            )
        )
    }
}