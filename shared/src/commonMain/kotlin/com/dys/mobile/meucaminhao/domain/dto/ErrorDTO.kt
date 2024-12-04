package com.dys.mobile.meucaminhao.domain.dto

data class ErrorDTO(
    val statusCode: Int,
    val errorCode: String?,
    val msg: String,
    val path: String?
) : Throwable()
