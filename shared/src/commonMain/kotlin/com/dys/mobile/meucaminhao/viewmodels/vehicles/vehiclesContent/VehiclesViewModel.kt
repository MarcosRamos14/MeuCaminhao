package com.dys.mobile.meucaminhao.viewmodels.vehicles.vehiclesContent

import com.dys.mobile.meucaminhao.navigation.event.Event
import com.dys.mobile.meucaminhao.navigation.event.NavigateTo
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VehiclesViewModel : BaseViewModel() {

    private val _vehiclesStateFlow = MutableStateFlow(VehiclesState())
    val vehiclesState = _vehiclesStateFlow.asStateFlow()

    // TODO: Trazer dados do resumo dos veículos. (Verificar como será para quem tiver 1 veículo)

    fun onEvent(event: Event) {
        when (event) {
            is NavigateTo -> {
                updateState { state ->
                    state.copy(navigation = event.route)
                }
            }
        }
    }
}