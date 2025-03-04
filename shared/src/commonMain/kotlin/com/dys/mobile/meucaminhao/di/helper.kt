package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.domain.EnvSet
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinApplication.() -> Unit = {}) {
    startKoin {
        appDeclaration.invoke(this)
        modules(
            sharedModules,
            module { single { EnvSet() } }
        )
    }
}

