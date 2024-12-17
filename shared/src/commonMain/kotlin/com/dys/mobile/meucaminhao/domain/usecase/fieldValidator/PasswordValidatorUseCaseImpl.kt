package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

class PasswordValidatorUseCaseImpl : FieldValidatorUseCase {

    // TODO: Implement password validator
    private val pattern = "".toRegex()

    override fun isValid(value: String): Boolean {
        return pattern.matches(value)
    }
}