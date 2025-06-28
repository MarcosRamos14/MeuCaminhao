package com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles

import com.dys.mobile.meucaminhao.domain.dto.VehicleDTO
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyVehiclesViewModel : BaseViewModel() {

    private val _myVehiclesStateFlow = MutableStateFlow(MyVehiclesState())
    val myVehiclesState = _myVehiclesStateFlow.asStateFlow()

    fun requestMyVehicles() {
        launchWithState {
            // TODO()
        }
    }

    fun mockedVehicleList() = listOf(
        VehicleDTO(
            id = 1
        ),
        VehicleDTO(
            id = 2
        ),
        VehicleDTO(
            id = 3
        ),
        VehicleDTO(
            id = 4
        ),
        VehicleDTO(
            id = 5
        )
    )

    fun onEvent(event: Event) {
        when (event) {
            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
        }
    }
}