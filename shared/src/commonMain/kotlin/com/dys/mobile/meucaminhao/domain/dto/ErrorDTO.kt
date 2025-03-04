package com.dys.mobile.meucaminhao.domain.dto

import com.dys.mobile.meucaminhao.domain.stringResources
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val statusCode: Int?,
    val errorCode: String?,
    val msg: String?,
    val path: String?
) : Throwable() {

    companion object {
        fun default(
            statusCode: Int? = null,
            errorCode: String? = null,
            path: String? = null
        ) = ErrorDTO(
            statusCode = statusCode,
            errorCode = errorCode,
            path = path,
            msg = stringResources.defaultErrorMessage
        )
    }
}
