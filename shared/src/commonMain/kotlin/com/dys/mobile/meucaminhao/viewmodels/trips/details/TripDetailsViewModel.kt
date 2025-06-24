package com.dys.mobile.meucaminhao.viewmodels.trips.details

import com.dys.mobile.meucaminhao.domain.dto.TripDTO
import com.dys.mobile.meucaminhao.domain.dto.TripGeneralInfoDTO
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
                driver = null,
                vehiclePlate = null,
                date = null,
                startTime = null,
                duration = null,
                manifestUrl = "https://www.thecampusqdl.com/uploads/files/pdf_sample_2.pdf",
                course = null,
                weight = null
            ),
            income = null,
            expenses = null,
            checklist = emptyList(),
            canEdit = true,
            canDelete = true
        )
    }
}