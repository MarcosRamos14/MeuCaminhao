package com.dys.mobile.meucaminhao.domain

actual class EnvSet actual constructor() {
    actual val baseUrl: String = "https://meucaminhao-56c12b7a28a2.herokuapp.com"
    actual val level: String = "DEBUG"
    actual val socketTimeout: Long = 60_000
    actual val requestTimeout: Long = 60_000
}