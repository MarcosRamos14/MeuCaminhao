package com.dys.mobile.meucaminhao.viewmodels.vehicles.common

sealed interface VehicleErrorState {
    data object InvalidPlate : VehicleErrorState
    data object EmptyPlate : VehicleErrorState
    data object EmptyBrand : VehicleErrorState
    data object EmptyModel : VehicleErrorState
}

data class VehicleErrors(
    val invalidPlate: VehicleError? = null,
    val emptyPlate: VehicleError? = null,
    val emptyBrand: VehicleError? = null,
    val emptyModel: VehicleError? = null
) {
    val hasAnyError: Boolean
        get() = invalidPlate != null || emptyPlate != null || emptyBrand != null || emptyModel != null
}

sealed class VehicleError {
    object Empty : VehicleError()
    object Invalid : VehicleError()
}