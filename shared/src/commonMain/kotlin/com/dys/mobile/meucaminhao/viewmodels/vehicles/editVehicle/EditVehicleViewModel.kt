package com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle

import com.dys.mobile.meucaminhao.data.vehicle.VehiclesRepository
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.VehicleValidatorUseCase
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleError
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleErrors
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.RequestVehicleForEdit
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.SaveChanges
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.BrandChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.ChecklistsChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.DriversChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.IsOpenSelectorPhoto
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.ModelChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.PhotoUriChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.editVehicle.EditVehicleEvent.PlateChanged
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditVehicleViewModel(
    private val vehiclesRepository: VehiclesRepository,
    private val validatorUseCase: VehicleValidatorUseCase
) : BaseViewModel() {

    private val _editVehicleStateFlow = MutableStateFlow(EditVehicleState())
    val editVehicleState = _editVehicleStateFlow.asStateFlow()

    fun onEvent(event: Event) {
        when (event) {
            is RequestVehicleForEdit -> requestVehicleForEdit(event.id)

            is PlateChanged -> _editVehicleStateFlow.updatePlate(event.plate)

            is BrandChanged -> _editVehicleStateFlow.updateBrand(event.brand)

            is ModelChanged -> _editVehicleStateFlow.updateModel(event.model)

            is PhotoUriChanged -> _editVehicleStateFlow.updatePhotoUri(event.photoUri)

            is DriversChanged -> _editVehicleStateFlow.updateLinkedDrivers(event.drivers)

            is ChecklistsChanged -> _editVehicleStateFlow.updateLinkedChecklists(event.checklists)

            is IsOpenSelectorPhoto -> _editVehicleStateFlow.updatePhotoSourceBottomSheet(event.isOpen)

            is SaveChanges -> validateData()

            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
        }
    }

    private fun requestVehicleForEdit(id: Long) {
        launchWithState {
            coroutineScope {
                val vehicleInfoDeferred = async { vehiclesRepository.requestVehicleForEdit(id) }
                val driversToLinkDeferred = async { vehiclesRepository.requestDriversToLink() }
                val checklistsToLinkDeferred = async { vehiclesRepository.requestChecklistToLink() }

                _editVehicleStateFlow.run {
                    toEditVehicleState(vehicleInfoDeferred.await())
                    updateDriversToLink(driversToLinkDeferred.await())
                    updateChecklistsToLink(checklistsToLinkDeferred.await())
                }
            }
        }
    }

    private fun validateData() = _editVehicleStateFlow.run {
        val state = value

        val errors = VehicleErrors(
            invalidPlate = if (!validatorUseCase.isPlateValid(state.plate)) VehicleError.Invalid else null,
            emptyPlate = if (state.plate.isBlank()) VehicleError.Empty else null,
            emptyBrand = if (state.brand.isBlank()) VehicleError.Empty else null,
            emptyModel = if (state.model.isBlank()) VehicleError.Empty else null
        )

        if (!errors.hasAnyError) {
            updateErrors(null)
            saveChanges()
        } else updateErrors(errors)
    }

    private fun saveChanges() {
        launchWithState {
            // TODO: Obter resultado da atualização para passar para UI.
            vehiclesRepository.editVehicle(_editVehicleStateFlow.value.toDto())
            _editVehicleStateFlow.updateFeedbackBottomSheet(true)
        }
    }
}