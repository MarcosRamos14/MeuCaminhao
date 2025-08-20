package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

interface VehicleValidatorUseCase {

    fun isPlateValid(value: String): Boolean
}

class VehicleValidatorUseCaseImpl : VehicleValidatorUseCase {

    private val plateRegex = Regex(
        pattern = "^[A-Z]{3}[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$",
        option = RegexOption.IGNORE_CASE
    )

    override fun isPlateValid(value: String): Boolean {
        return plateRegex.matches(value.trim().replace("-", ""))
    }
}