package com.dys.mobile.meucaminhao.data.base

import com.dys.mobile.meucaminhao.domain.applicationLogger
import com.dys.mobile.meucaminhao.domain.dto.ErrorDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

fun HttpStatusCode.isServerError(): Boolean = this.value in (500..599)

const val SERVER_ERROR_CODE = "server.error"

// todo: 1. Receber uma classe (ou callback) para verificar a conexão.
abstract class Service : KoinComponent {

    val client: HttpClient by lazy { httpClient(sessionManager = get()) }

    protected suspend inline fun <reified T> doRequest(crossinline request: suspend () -> HttpResponse): T? {
        return withContext(Dispatchers.IO) {
            val response = try {
                request()
            } catch (throwable: Throwable) {
                applicationLogger.e(
                    message = throwable.stackTraceToString(),
                    tag = this::class.simpleName.toString()
                )
                throw ErrorDTO.default()
            }

            if (response.status.isSuccess()) {
                return@withContext response.body<T>()
            }

            if (response.status.isServerError()) {
                throw ErrorDTO.default(
                    statusCode = response.status.value,
                    errorCode = SERVER_ERROR_CODE,
                    path = response.call.request.url.encodedPath
                )
            }

            throw response.body<ErrorDTO>()
        }
    }
}