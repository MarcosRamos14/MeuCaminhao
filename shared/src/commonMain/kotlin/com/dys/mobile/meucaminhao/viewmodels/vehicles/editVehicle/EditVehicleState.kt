package com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle

import com.dys.mobile.meucaminhao.domain.dto.vehicle.ChecklistToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.DriverToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.EditVehicleDTO
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleErrors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class EditVehicleState(
    val id: Long = 0,
    val plate: String = "",
    val brand: String = "",
    val model: String = "",
    val photoUri: String? = null,
    val linkedDrivers: List<DriverToLinkDTO>? = null,
    val linkedChecklists: List<ChecklistToLinkDTO>? = null,
    val driversToLink: List<DriverToLinkDTO>? = null,
    val checklistsToLink: List<ChecklistToLinkDTO>? = null,
    val isOpenFeedbackBottomSheet: Boolean = false,
    val isOpenPhotoSourceBottomSheet: Boolean = false,
    val errors: VehicleErrors? = null
)

fun EditVehicleState.toDto() = EditVehicleDTO(
    id = this.id,
    plate = this.plate,
    brand = this.brand,
    model = this.model,
    photoUri = this.photoUri,
    drivers = this.linkedDrivers,
    checklists = this.linkedChecklists
)

fun MutableStateFlow<EditVehicleState>.toEditVehicleState(vehicleInfo: EditVehicleDTO) {
    update {
        it.copy(
            id = vehicleInfo.id,
            plate = vehicleInfo.plate,
            brand = vehicleInfo.brand,
            model = vehicleInfo.model,
            photoUri = vehicleInfo.photoUri,
            linkedDrivers = vehicleInfo.drivers,
            linkedChecklists = vehicleInfo.checklists
        )
    }
}

fun MutableStateFlow<EditVehicleState>.updatePlate(plate: String) {
    update { it.copy(plate = plate) }
}

fun MutableStateFlow<EditVehicleState>.updateBrand(brand: String) {
    update { it.copy(brand = brand) }
}

fun MutableStateFlow<EditVehicleState>.updateModel(model: String) {
    update { it.copy(model = model) }
}

fun MutableStateFlow<EditVehicleState>.updatePhotoUri(photoUri: String) {
    update { it.copy(photoUri = photoUri) }
}

fun MutableStateFlow<EditVehicleState>.updateLinkedDrivers(linkedDrivers: List<DriverToLinkDTO>) {
    update { it.copy(linkedDrivers = linkedDrivers) }
}

fun MutableStateFlow<EditVehicleState>.updateLinkedChecklists(linkedChecklists: List<ChecklistToLinkDTO>) {
    update { it.copy(linkedChecklists = linkedChecklists) }
}

fun MutableStateFlow<EditVehicleState>.updateDriversToLink(driversToLink: List<DriverToLinkDTO>) {
    update { it.copy(driversToLink = driversToLink) }
}

fun MutableStateFlow<EditVehicleState>.updateChecklistsToLink(checklistsToLink: List<ChecklistToLinkDTO>) {
    update { it.copy(checklistsToLink = checklistsToLink) }
}

fun MutableStateFlow<EditVehicleState>.updateFeedbackBottomSheet(isOpen: Boolean) {
    update { it.copy(isOpenFeedbackBottomSheet = isOpen) }
}

fun MutableStateFlow<EditVehicleState>.updatePhotoSourceBottomSheet(isOpen: Boolean) {
    update { it.copy(isOpenPhotoSourceBottomSheet = isOpen) }
}

fun MutableStateFlow<EditVehicleState>.updateErrors(errors: VehicleErrors?) {
    update { it.copy(errors = errors) }
}