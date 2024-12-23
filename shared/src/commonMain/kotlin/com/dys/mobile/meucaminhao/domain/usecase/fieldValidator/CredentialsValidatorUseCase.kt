package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

interface CredentialsValidatorUseCase {

    fun isPasswordValid(value: String): Boolean
}