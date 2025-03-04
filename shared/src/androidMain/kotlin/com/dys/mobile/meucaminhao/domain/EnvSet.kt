package com.dys.mobile.meucaminhao.domain

actual class EnvSet actual constructor() {
    // todo: localhost - substituir após subir serviços
    actual val baseUrl: String = "http://10.0.2.2:8080"
    actual val level: String = "DEBUG"
    actual val socketTimeout: Long = 60_000
    actual val requestTimeout: Long = 60_000
}