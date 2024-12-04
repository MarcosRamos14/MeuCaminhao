package com.dys.mobile.meucaminhao.domain.usecase

interface FieldValidatorUseCase {
    fun isValid(value: String): Boolean
}