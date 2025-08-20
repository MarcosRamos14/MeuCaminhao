package com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle

import com.dys.mobile.meucaminhao.domain.dto.vehicle.ChecklistToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.DriverToLinkDTO
import com.dys.mobile.meucaminhao.navigation.event.Event

interface NewVehicleEvent : Event {

    data object CreateNewVehicle : NewVehicleEvent
    data class IsOpenSelectorPhoto(val isOpen: Boolean) : NewVehicleEvent
    data class PlateChanged(val plate: String) : NewVehicleEvent
    data class BrandChanged(val brand: String) : NewVehicleEvent
    data class ModelChanged(val model: String) : NewVehicleEvent
    data class PhotoUriChanged(val photoUri: String) : NewVehicleEvent
    data class DriversChanged(val drivers: List<DriverToLinkDTO>) : NewVehicleEvent
    data class ChecklistsChanged(val checklists: List<ChecklistToLinkDTO>) : NewVehicleEvent
}