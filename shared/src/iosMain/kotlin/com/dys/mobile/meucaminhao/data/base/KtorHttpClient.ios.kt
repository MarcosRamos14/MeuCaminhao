package com.dys.mobile.meucaminhao.data.base

import com.dys.mobile.meucaminhao.domain.SessionManager
import com.dys.mobile.meucaminhao.domain.applicationLogger
import com.dys.mobile.meucaminhao.domain.EnvSet
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun httpClient(
    sessionManager: SessionManager,
    envSet: EnvSet,
    vararg customPlugin: ClientPlugin<Unit>
): HttpClient {
    return HttpClient(Darwin) {
        install(HttpTimeout) {
            socketTimeoutMillis = envSet.socketTimeout
            requestTimeoutMillis = envSet.requestTimeout
        }

        install(Logging) {
            level = when (envSet.level) {
                "DEBUG" -> LogLevel.ALL
                else -> LogLevel.NONE
            }
            logger = object : Logger {
                override fun log(message: String) {
                    applicationLogger.d("HttpClient", message)
                }
            }
        }

        defaultRequest {
            header("Content-Type", "application/json")
            url(envSet.baseUrl)
        }

        engine {
            configureSession {
                sessionManager.getCredentials()?.let { credentials ->
                    headers {
                        set("Authorization", "Bearer ${credentials.authorizationToken}")
                        set("refresh-token", "Bearer ${credentials.refreshToken}")
                    }
                }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        for (plugin in customPlugin) {
            install(plugin)
        }
    }
}