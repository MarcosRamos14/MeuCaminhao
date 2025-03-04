package com.dys.mobile.meucaminhao.data.authentication

import com.dys.mobile.meucaminhao.data.base.Service
import com.dys.mobile.meucaminhao.domain.SessionManager
import com.dys.mobile.meucaminhao.domain.dto.CredentialsDTO
import com.dys.mobile.meucaminhao.domain.dto.UserDTO
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.Serializable

class AuthenticationRepository(private val sessionManager: SessionManager) : Service() {

    @Serializable
    data class AuthRequestBody(val email: String, val password: String)

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val REFRESH_TOKEN = "refresh-token"
    }

    suspend fun authenticate(email: String, password: String): UserDTO? {
        return doRequest<UserDTO> {
            val response = client.post("/auth/login") {
                setBody(AuthRequestBody(email, password))
            }
            val newCredentials = CredentialsDTO(
                authorizationToken = response.headers[AUTHORIZATION_HEADER].orEmpty(),
                refreshToken = response.headers[REFRESH_TOKEN].orEmpty()
            )
            sessionManager.updateCredentials(newCredentials)
            return@doRequest response
        }
    }
}