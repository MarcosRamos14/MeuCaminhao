package com.dys.mobile.meucaminhao.domain

import android.util.Log

private object AndroidLogger : Logger {
    // todo - usar o timber
    override fun d(message: String, tag: String) {
        Log.d(tag, message)
    }

    override fun w(message: String, tag: String) {
        Log.w(tag, message)
    }

    override fun e(message: String, tag: String) {
        Log.e(tag, message)
    }

    override fun i(message: String, tag: String) {
        Log.i(tag, message)
    }

}

actual val applicationLogger: Logger get() = AndroidLogger