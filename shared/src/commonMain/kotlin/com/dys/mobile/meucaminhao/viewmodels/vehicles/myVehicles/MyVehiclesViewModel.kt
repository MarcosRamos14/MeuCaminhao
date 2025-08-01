package com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles

import com.dys.mobile.meucaminhao.data.vehicle.VehiclesRepository
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles.MyVehiclesEvent.RequestMyVehicles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyVehiclesViewModel(
    private val vehicleRepository: VehiclesRepository
) : BaseViewModel() {

    private val _myVehiclesStateFlow = MutableStateFlow(MyVehiclesState())
    val myVehiclesState = _myVehiclesStateFlow.asStateFlow()

    fun onEvent(event: Event) {
        when (event) {
            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
            RequestMyVehicles -> requestMyVehicles()
        }
    }

    private fun requestMyVehicles() {
        launchWithState {
            val vehicles = vehicleRepository.requestMyVehicles()
            _myVehiclesStateFlow.updateVehicles(vehicles = vehicles)
        }
    }
}