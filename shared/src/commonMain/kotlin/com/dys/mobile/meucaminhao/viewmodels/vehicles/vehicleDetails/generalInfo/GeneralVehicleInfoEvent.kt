package com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo

import com.dys.mobile.meucaminhao.navigation.event.Event

interface GeneralVehicleInfoEvent : Event {
    data class RequestGeneralVehicleInfo(val id: Long): GeneralVehicleInfoEvent
    data object OpenDeletionBottomSheet: GeneralVehicleInfoEvent
    data object CloseDeletionBottomSheet: GeneralVehicleInfoEvent
    data object CloseConfirmationDeletionBottomSheet: GeneralVehicleInfoEvent
    data class DeleteVehicle(val id: Long): GeneralVehicleInfoEvent
}