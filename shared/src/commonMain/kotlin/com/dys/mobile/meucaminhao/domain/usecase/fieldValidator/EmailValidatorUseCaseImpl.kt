package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

class EmailValidatorUseCaseImpl : FieldValidatorUseCase {

    // TODO: Implement email validator
    private val pattern = "".toRegex()

    override fun isValid(value: String): Boolean {
        return pattern.matches(value)
    }
}