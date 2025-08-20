package com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle

import com.dys.mobile.meucaminhao.domain.dto.vehicle.ChecklistToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.DriverToLinkDTO
import com.dys.mobile.meucaminhao.navigation.event.Event

interface EditVehicleEvent : Event {

    data class RequestVehicleForEdit(val id: Long) : Event
    data class IsOpenSelectorPhoto(val isOpen: Boolean) : Event
    data class PlateChanged(val plate: String) : Event
    data class BrandChanged(val brand: String) : Event
    data class ModelChanged(val model: String) : Event
    data class PhotoUriChanged(val photoUri: String) : Event
    data class DriversChanged(val drivers: List<DriverToLinkDTO>) : Event
    data class ChecklistsChanged(val checklists: List<ChecklistToLinkDTO>) : Event
    data object SaveChanges : Event
}