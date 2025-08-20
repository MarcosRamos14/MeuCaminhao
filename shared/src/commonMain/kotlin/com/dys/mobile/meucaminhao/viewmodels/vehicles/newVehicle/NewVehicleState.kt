package com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle

import com.dys.mobile.meucaminhao.domain.dto.vehicle.ChecklistToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.DriverToLinkDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class NewVehicleState(
    val plate: String = "",
    val brand: String = "",
    val model: String = "",
    val photoUri: String? = null,
    val drivers: List<DriverToLinkDTO>? = null,
    val checklists: List<ChecklistToLinkDTO>? = null,
    val isOpenFeedbackBottomSheet: Boolean = false,
    val isOpenPhotoSourceBottomSheet: Boolean = false,
    val error: NewVehicleErrorState? = null
)

sealed interface NewVehicleErrorState {
    data object InvalidPlate : NewVehicleErrorState
    data object EmptyPlate : NewVehicleErrorState
    data object EmptyBrand : NewVehicleErrorState
    data object EmptyModel : NewVehicleErrorState
}

fun MutableStateFlow<NewVehicleState>.updatePlate(plate: String) {
    update { it.copy(plate = plate) }
}

fun MutableStateFlow<NewVehicleState>.updateBrand(brand: String) {
    update { it.copy(brand = brand) }
}

fun MutableStateFlow<NewVehicleState>.updateModel(model: String) {
    update { it.copy(model = model) }
}

fun MutableStateFlow<NewVehicleState>.updatePhotoUri(photoUri: String) {
    update { it.copy(photoUri = photoUri) }
}

fun MutableStateFlow<NewVehicleState>.updateDrivers(drivers: List<DriverToLinkDTO>) {
    update { it.copy(drivers = drivers) }
}

fun MutableStateFlow<NewVehicleState>.updateChecklists(checklists: List<ChecklistToLinkDTO>) {
    update { it.copy(checklists = checklists) }
}

fun MutableStateFlow<NewVehicleState>.updateFeedbackBottomSheet(isOpen: Boolean) {
    update { it.copy(isOpenFeedbackBottomSheet = isOpen) }
}

fun MutableStateFlow<NewVehicleState>.updatePhotoSourceBottomSheet(isOpen: Boolean) {
    update { it.copy(isOpenPhotoSourceBottomSheet = isOpen) }
}

fun MutableStateFlow<NewVehicleState>.updateError(error: NewVehicleErrorState?) {
    update { it.copy(error = error) }
}