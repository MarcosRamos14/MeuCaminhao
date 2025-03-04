package com.dys.mobile.meucaminhao.domain

import android.app.Application
import com.dys.mobile.meucaminhao.shared.R
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object Resources : KoinComponent {

    private val ctx: Application by inject()

    actual object Strings {
        actual val defaultErrorMessage: String
            get() = ctx.getString(R.string.default_error_message)
    }
}