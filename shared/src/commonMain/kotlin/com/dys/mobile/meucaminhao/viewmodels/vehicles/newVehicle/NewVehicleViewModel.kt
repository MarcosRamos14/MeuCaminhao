package com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle

import com.dys.mobile.meucaminhao.data.vehicle.VehiclesRepository
import com.dys.mobile.meucaminhao.domain.dto.vehicle.NewVehicleDTO
import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.VehicleValidatorUseCase
import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleErrorState.EmptyBrand
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleErrorState.EmptyModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleErrorState.EmptyPlate
import com.dys.mobile.meucaminhao.viewmodels.vehicles.common.VehicleErrorState.InvalidPlate
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.BrandChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.ChecklistsChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.CreateNewVehicle
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.DriversChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.ModelChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.IsOpenSelectorPhoto
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.PhotoUriChanged
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleEvent.PlateChanged
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewVehicleViewModel(
    private val vehicleRepository: VehiclesRepository,
    private val validatorUseCase: VehicleValidatorUseCase
) : BaseViewModel() {

    private val _newVehicleStateFlow = MutableStateFlow(NewVehicleState())
    val newVehicleState = _newVehicleStateFlow.asStateFlow()

    init {
        requestAvailableDriversAndChecklist()
    }

    fun onEvent(event: Event) {
        when (event) {
            is PlateChanged -> _newVehicleStateFlow.updatePlate(event.plate)

            is BrandChanged -> _newVehicleStateFlow.updateBrand(event.brand)

            is ModelChanged -> _newVehicleStateFlow.updateModel(event.model)

            is PhotoUriChanged -> _newVehicleStateFlow.updatePhotoUri(event.photoUri)

            is DriversChanged -> _newVehicleStateFlow.updateDrivers(event.drivers)

            is ChecklistsChanged -> _newVehicleStateFlow.updateChecklists(event.checklists)

            is IsOpenSelectorPhoto -> _newVehicleStateFlow.updatePhotoSourceBottomSheet(event.isOpen)

            is CreateNewVehicle -> validateData()

            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
        }
    }

    private fun requestAvailableDriversAndChecklist() {
        launchWithState {
            val drivers = vehicleRepository.requestDriversToLink()
            val checklists = vehicleRepository.requestChecklistToLink()

            _newVehicleStateFlow.updateDrivers(drivers)
            _newVehicleStateFlow.updateChecklists(checklists)
        }
    }

    private fun validateData() = _newVehicleStateFlow.run {
        val state = value

        when {
            state.plate.isBlank() -> updateError(EmptyPlate)
            state.brand.isBlank() -> updateError(EmptyBrand)
            state.model.isBlank() -> updateError(EmptyModel)
            !validatorUseCase.isPlateValid(state.plate) -> updateError(InvalidPlate)
            else -> {
                updateError(null)
                createNewVehicle()
            }
        }
    }

    private fun createNewVehicle() {
        launchWithState {
            val state = _newVehicleStateFlow.value

            state.photoUri?.let {
                 // TODO: Upload
            }

            val newVehicle = NewVehicleDTO(
                model = state.model,
                brand = state.brand,
                plate = state.plate,
                photoUri = state.photoUri,
                drivers = state.drivers,
                checklists = state.checklists
            )

            vehicleRepository.createNewVehicle(newVehicle)
            _newVehicleStateFlow.updateFeedbackBottomSheet(true)
        }
    }
}