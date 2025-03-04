package com.dys.mobile.meucaminhao.data.base

import com.dys.mobile.meucaminhao.domain.SessionManager
import com.dys.mobile.meucaminhao.domain.applicationLogger
import com.dys.mobile.meucaminhao.domain.EnvSet
import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.request

val loggerPlugin = createClientPlugin("LoggerPlugin") {
    onRequest { request, _ ->
        applicationLogger.d("LoggerPlugin", "=============REQUEST==============")
        applicationLogger.d("LoggerPlugin", "${request.method.value} => ${request.url}")
        applicationLogger.d("LoggerPlugin", "BODY => ${request.body}")
        applicationLogger.d("LoggerPlugin", "=============END-REQUEST==============")
    }
    onResponse { response ->
        applicationLogger.d("LoggerPlugin", "=============RESPONSE==============")
        applicationLogger.d("LoggerPlugin", "${response.request.method.value} / ${response.status} => ${response.request.url}")
        applicationLogger.d("LoggerPlugin", "BODY => $response")
        applicationLogger.d("LoggerPlugin", "=============END-RESPONSE==============")
    }
}

expect fun httpClient(
    sessionManager: SessionManager,
    envSet: EnvSet = EnvSet(),
    vararg customPlugin: ClientPlugin<Unit> = arrayOf(loggerPlugin)
): HttpClient
