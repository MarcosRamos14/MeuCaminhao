package com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles

import com.dys.mobile.meucaminhao.navigation.event.Event

interface MyVehiclesEvent : Event {
    data object RequestMyVehicles : MyVehiclesEvent
}