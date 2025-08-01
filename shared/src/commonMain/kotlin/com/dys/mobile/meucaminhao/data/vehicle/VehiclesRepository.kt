package com.dys.mobile.meucaminhao.data.vehicle

import com.dys.mobile.meucaminhao.domain.dto.ComponentDTO
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleChecklistDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleDriverDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleInfoDTO

interface VehiclesRepository {

    suspend fun requestMyVehicles(): List<VehicleDTO>

    suspend fun requestVehicleInfoById(id: Long): VehicleInfoDTO

    suspend fun deleteVehicleById(id: Long)
}

class VehiclesRepositoryImpl : VehiclesRepository {

    override suspend fun requestMyVehicles(): List<VehicleDTO> {
        return listOf(
            VehicleDTO(
                id = 1,
                plate = "AAA-1111",
                createdAt = "Ago 23, 2025",
                totalIncome = TotalAmountDTO(
                    value = 7423.47,
                    currency = null,
                    formatted = "R$ 7.423,47",
                    isPositive = true
                ),
                totalExpense = TotalAmountDTO(
                    value = 2157.18,
                    currency = null,
                    formatted = "R$ 2.157,18",
                    isPositive = false
                )
            )
        )
    }

    override suspend fun requestVehicleInfoById(id: Long): VehicleInfoDTO {
        return VehicleInfoDTO(
            id = 1,
            plate = "AAA-1111",
            brand = "Scania",
            model = "R450",
            createdAt = "Ago 23, 2025",
            photoUrl = "https://picsum.photos/200/300/?blur",
            history = listOf(
                ComponentDTO(
                    overline = "Receita total",
                    value = "R$ 7.423,47",
                    accentColor = "FF23817A",
                    backgroundColor = ""
                ),
                ComponentDTO(
                    overline = "Despesa total",
                    value = "R$ 2.157,18",
                    accentColor = "FFE23B58",
                    backgroundColor = ""
                ),
                ComponentDTO(
                    overline = "Viagens",
                    value = "471",
                    accentColor = "FF007AFF",
                    backgroundColor = ""
                ),
                ComponentDTO(
                    overline = "Manutenções",
                    value = "13",
                    accentColor = "FFE29218",
                    backgroundColor = ""
                )
            ),
            drivers = listOf(
                VehicleDriverDTO(
                    id = 1,
                    name = "Marcos Ramos",
                    photoUrl = "https://picsum.photos/id/237/200/300",
                    leftInfo = ComponentDTO(
                        overline = "Data de cadastro",
                        value = "Ago 23, 2025",
                        accentColor = "",
                        backgroundColor = ""
                    ),
                    rightInfo = ComponentDTO(
                        overline = "Qtd. viagens",
                        value = "845",
                        accentColor = "",
                        backgroundColor = ""
                    )
                ),
                VehicleDriverDTO(
                    id = 1,
                    name = "Yuri Barbosa",
                    photoUrl = "",
                    leftInfo = ComponentDTO(
                        overline = "Data de cadastro",
                        value = "Ago 23, 2025",
                        accentColor = "",
                        backgroundColor = ""
                    ),
                    rightInfo = ComponentDTO(
                        overline = "Qtd. viagens",
                        value = "745",
                        accentColor = "",
                        backgroundColor = ""
                    )
                )
            ),
            checklists = listOf(
                VehicleChecklistDTO(
                    id = 1,
                    name = "Revisão pré-viagem",
                    lastReview = "Ago 23, 2025",
                    periodicity = "A cada viagem"
                ),
                VehicleChecklistDTO(
                    id = 1,
                    name = "Revisão preventiva",
                    lastReview = "Ago 23, 2025",
                    periodicity = "A cada 15 dias"
                )
            )
        )
    }

    override suspend fun deleteVehicleById(id: Long) {
        TODO("Not yet implemented")
    }
}