package com.dys.mobile.meucaminhao.domain

interface Logger {
    fun d(message: String, tag: String = "")
    fun w(message: String, tag: String = "")
    fun e(message: String, tag: String = "")
    fun i(message: String, tag: String = "")
}

expect val applicationLogger: Logger