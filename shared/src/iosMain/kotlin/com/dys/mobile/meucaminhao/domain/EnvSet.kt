package com.dys.mobile.meucaminhao.domain

actual class EnvSet actual constructor() {
    // todo: localhost - substituir após subir serviços
    actual val baseUrl: String = "http://127.0.0.1:8080"
    actual val level: String = "DEBUG"
    actual val socketTimeout: Long = 60_000
    actual val requestTimeout: Long = 60_000
}