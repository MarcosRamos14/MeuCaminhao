package com.dys.mobile.meucaminhao.data.vehicle

import com.dys.mobile.meucaminhao.domain.dto.ComponentDTO
import com.dys.mobile.meucaminhao.domain.dto.TotalAmountDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.ChecklistToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.DriverToLinkDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.EditVehicleDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleChecklistDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleDriverDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.VehicleInfoDTO
import com.dys.mobile.meucaminhao.domain.dto.vehicle.NewVehicleDTO

interface VehiclesRepository {

    suspend fun createNewVehicle(newVehicle: NewVehicleDTO)

    suspend fun requestDriversToLink(): List<DriverToLinkDTO>

    suspend fun requestChecklistToLink(): List<ChecklistToLinkDTO>

    suspend fun requestMyVehicles(): List<VehicleDTO>

    suspend fun requestVehicleInfoById(id: Long): VehicleInfoDTO

    suspend fun deleteVehicleById(id: Long)

    suspend fun editVehicle(vehicle: EditVehicleDTO)

    suspend fun requestVehicleForEdit(id: Long): EditVehicleDTO
}

class VehiclesRepositoryImpl : VehiclesRepository {

    override suspend fun createNewVehicle(newVehicle: NewVehicleDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun requestDriversToLink(): List<DriverToLinkDTO> {
        return listOf(
            DriverToLinkDTO(
                id = 1,
                name = "Marcos Ramos"
            ),
            DriverToLinkDTO(
                id = 2,
                name = "José da Silva"
            )
        )
    }

    override suspend fun requestChecklistToLink(): List<ChecklistToLinkDTO> {
        return listOf(
            ChecklistToLinkDTO(
                id = 1,
                name = "Revisão pré-viagem"
            ),
            ChecklistToLinkDTO(
                id = 2,
                name = "Revisão preventiva"
            )
        )
    }

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
            ),
            VehicleDTO(
                id = 2,
                plate = "BBB-2222",
                createdAt = "Ago 21, 2025",
                totalIncome = TotalAmountDTO(
                    value = 7874.47,
                    currency = null,
                    formatted = "R$ 7.874,47",
                    isPositive = true
                ),
                totalExpense = TotalAmountDTO(
                    value = 12157.18,
                    currency = null,
                    formatted = "R$ 12.157,18",
                    isPositive = false
                )
            ),
            VehicleDTO(
                id = 3,
                plate = "CCC-3333",
                createdAt = "Jan 02, 2025",
                totalIncome = TotalAmountDTO(
                    value = 488.07,
                    currency = null,
                    formatted = "R$ 488,07",
                    isPositive = true
                ),
                totalExpense = TotalAmountDTO(
                    value = 57.18,
                    currency = null,
                    formatted = "R$ 57,18",
                    isPositive = false
                )
            ),
            VehicleDTO(
                id = 4,
                plate = "DDD-4444",
                createdAt = "Sep 05, 2024",
                totalIncome = TotalAmountDTO(
                    value = 1575.48,
                    currency = null,
                    formatted = "R$ 1.575,48",
                    isPositive = true
                ),
                totalExpense = TotalAmountDTO(
                    value = 2157.18,
                    currency = null,
                    formatted = "R$ 2.157,18",
                    isPositive = false
                )
            ),
            VehicleDTO(
                id = 5,
                plate = "EEE-5555",
                createdAt = "Jul 16, 2025",
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
            photoUrl = "https://static.vecteezy.com/ti/fotos-gratis/t2/27843401-uma-carga-caminhao-com-uma-recipiente-e-visto-dirigindo-atraves-uma-ponte-enquanto-uma-semi-caminhao-com-uma-carga-reboque-segue-de-perto-atras-foto.jpg",
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
                    name = "Jose Lopes",
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

    override suspend fun editVehicle(vehicle: EditVehicleDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun requestVehicleForEdit(id: Long): EditVehicleDTO {
        return EditVehicleDTO(
            id = 1,
            plate = "AAA-1111",
            brand = "Scania",
            model = "R450",
            photoUri = "",
            drivers = listOf(
                DriverToLinkDTO(
                    id = 1,
                    name = "Marcos Ramos"
                )
            ),
            checklists = emptyList()
        )
    }
}