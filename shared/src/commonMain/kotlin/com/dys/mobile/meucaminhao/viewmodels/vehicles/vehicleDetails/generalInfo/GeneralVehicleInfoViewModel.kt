package com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo

import com.dys.mobile.meucaminhao.data.vehicle.VehiclesRepository
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.CloseConfirmationDeletionBottomSheet
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.CloseDeletionBottomSheet
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.DeleteVehicle
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.OpenDeletionBottomSheet
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoEvent.RequestGeneralVehicleInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneralVehicleInfoViewModel(
    private val vehiclesRepository: VehiclesRepository
) : BaseViewModel() {

    private val _generalVehicleInfoStateFlow = MutableStateFlow(GeneralVehicleInfoState())
    val generalVehicleInfoState = _generalVehicleInfoStateFlow.asStateFlow()

    fun onEvent(event: Event) {
        when (event) {
            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
            is RequestGeneralVehicleInfo -> {
                requestGeneralVehicleInfo(event.id)
            }
            is OpenDeletionBottomSheet -> {
                _generalVehicleInfoStateFlow.updateDeletionBottomSheet(true)
            }
            is CloseDeletionBottomSheet -> {
                _generalVehicleInfoStateFlow.updateDeletionBottomSheet(false)
            }
            is DeleteVehicle -> {
                _generalVehicleInfoStateFlow.updateDeletionBottomSheet(false)
                deleteVehicleById(event.id)
            }
            is CloseConfirmationDeletionBottomSheet -> {
                _generalVehicleInfoStateFlow.updateConfirmationDeletionBottomSheet(false)
            }
        }
    }

    private fun requestGeneralVehicleInfo(id: Long) {
        launchWithState {
            val vehicleInfo = vehiclesRepository.requestVehicleInfoById(id)
            _generalVehicleInfoStateFlow.updateVehicleInfo(vehicleInfo)
        }
    }

    private fun deleteVehicleById(id: Long) {
        launchWithState {
            vehiclesRepository.deleteVehicleById(id) // TODO: Obter retorno para chamar updateConfirmationDeletionBottomSheet()
            _generalVehicleInfoStateFlow.updateConfirmationDeletionBottomSheet(true)
        }
    }
}