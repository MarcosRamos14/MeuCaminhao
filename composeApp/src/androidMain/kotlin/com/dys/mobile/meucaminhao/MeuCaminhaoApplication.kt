package com.dys.mobile.meucaminhao

import android.app.Application
import com.dys.mobile.meucaminhao.di.initKoin
import com.dys.mobile.toolkit.config.DisplayConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MeuCaminhaoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DisplayConfig.onConfigChanged(this)
        initKoin {
            androidContext(this@MeuCaminhaoApplication)
            androidLogger(Level.DEBUG)
        }
    }
}