package com.dys.mobile.meucaminhao.domain

actual val applicationLogger: Logger
    // todo
    get() = object : Logger {
        override fun d(message: String, tag: String) {
        }

        override fun w(message: String, tag: String) {
        }

        override fun e(message: String, tag: String) {
        }

        override fun i(message: String, tag: String) {
        }

    }