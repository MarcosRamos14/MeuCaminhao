package com.dys.mobile.meucaminhao.domain.usecase.fieldValidator

interface FieldValidatorUseCase {
    fun isValid(value: String): Boolean
}