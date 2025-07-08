package com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles

import com.dys.mobile.meucaminhao.domain.dto.VehicleDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class MyVehiclesState(
    val vehicles: List<VehicleDTO> = emptyList()
)

fun MutableStateFlow<MyVehiclesState>.updateVehicles(vehicles: List<VehicleDTO>) {
    update { it.copy(vehicles = vehicles) }
}