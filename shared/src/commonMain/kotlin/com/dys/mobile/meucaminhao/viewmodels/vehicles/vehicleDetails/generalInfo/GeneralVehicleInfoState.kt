package com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo

import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleInfoDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class GeneralVehicleInfoState(
    val vehicleInfo: VehicleInfoDTO? = null,
    val openDeletionBottomSheet: Boolean = false,
    val openConfirmationDeletionBottomSheet: Boolean = false
)

fun MutableStateFlow<GeneralVehicleInfoState>.updateVehicleInfo(vehicleInfo: VehicleInfoDTO) {
    update { it.copy(vehicleInfo = vehicleInfo) }
}

fun MutableStateFlow<GeneralVehicleInfoState>.updateDeletionBottomSheet(isOpen: Boolean) {
    update { it.copy(openDeletionBottomSheet = isOpen) }
}

fun MutableStateFlow<GeneralVehicleInfoState>.updateConfirmationDeletionBottomSheet(isOpen: Boolean) {
    update { it.copy(openConfirmationDeletionBottomSheet = isOpen) }
}