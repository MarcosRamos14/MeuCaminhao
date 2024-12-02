package com.dys.mobile.meucaminhao.domain.usecase

class PasswordValidatorUseCaseImpl : FieldValidatorUseCase {

    private val pattern = "".toRegex()

    override fun isValid(value: String): Boolean {
        return pattern.matches(value)
    }
}