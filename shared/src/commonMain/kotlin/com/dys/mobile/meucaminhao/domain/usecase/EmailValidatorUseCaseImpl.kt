package com.dys.mobile.meucaminhao.domain.usecase

class EmailValidatorUseCaseImpl : FieldValidatorUseCase {

    private val pattern = "".toRegex()

    override fun isValid(value: String): Boolean {
        return pattern.matches(value)
    }
}