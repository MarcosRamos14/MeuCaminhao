package com.dys.mobile.meucaminhao

import android.app.Application
import com.dys.mobile.meucaminhao.di.useCaseModule
import com.dys.mobile.meucaminhao.di.viewModelModule
import com.dys.mobile.toolkit.config.DisplayConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MeuCaminhaoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DisplayConfig.onConfigChanged(this)
        startKoin {
            androidContext(this@MeuCaminhaoApplication)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, useCaseModule)
        }
    }
}