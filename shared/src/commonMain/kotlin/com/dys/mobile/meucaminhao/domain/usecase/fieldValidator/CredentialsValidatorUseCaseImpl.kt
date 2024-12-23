package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

class CredentialsValidatorUseCaseImpl : CredentialsValidatorUseCase {

    private val pattern = ".{6,}".toRegex()

    override fun isPasswordValid(value: String): Boolean {
        return pattern.matches(value)
    }
}