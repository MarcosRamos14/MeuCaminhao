package com.dys.mobile.meucaminhao.domain.dto

data class ErrorDTO(
    val code: String,
    override val message: String,
) : Throwable()