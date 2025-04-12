package com.dys.mobile.meucaminhao.viewmodels.trips

import com.dys.mobile.meucaminhao.domain.state.launchWithState
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel

class TripsViewModel : BaseViewModel() {

    init {
        requestAllTrips()
    }

    private fun requestAllTrips() {
        launchWithState {
            //TODO: Request to API all trips
        }
    }
}