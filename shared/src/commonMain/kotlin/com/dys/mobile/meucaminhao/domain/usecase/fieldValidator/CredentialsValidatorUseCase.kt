package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

interface CredentialsValidatorUseCase {

    fun isEmailValid(value: String): Boolean

    fun isPasswordValid(value: String): Boolean
}