package com.dys.mobile.meucaminhao.domain

expect class EnvSet() {
    val baseUrl: String
    val level: String
    val socketTimeout: Long
    val requestTimeout: Long
}