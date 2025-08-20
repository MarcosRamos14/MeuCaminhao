package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

interface CredentialsValidatorUseCase {

    fun isEmailValid(value: String): Boolean

    fun isPasswordValid(value: String): Boolean
}

class CredentialsValidatorUseCaseImpl : CredentialsValidatorUseCase {

    private val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
    private val passwordPattern = ".{6,}".toRegex()

    override fun isEmailValid(value: String): Boolean {
        return emailPattern.matches(value)
    }

    override fun isPasswordValid(value: String): Boolean {
        return passwordPattern.matches(value)
    }
}