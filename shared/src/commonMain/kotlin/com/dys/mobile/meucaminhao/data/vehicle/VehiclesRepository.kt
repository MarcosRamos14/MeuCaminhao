package com.dys.mobile.meucaminhao.data.vehicle

import com.dys.mobile.meucaminhao.domain.dto.VehicleDTO

interface VehiclesRepository {

    suspend fun requestMyVehicles(): List<VehicleDTO>
}

class VehiclesRepositoryImpl : VehiclesRepository {

    override suspend fun requestMyVehicles(): List<VehicleDTO> {
        return listOf(
            VehicleDTO(
                id = 1,
                licensePlate = "AAA-1111"
            )
        )
    }
}