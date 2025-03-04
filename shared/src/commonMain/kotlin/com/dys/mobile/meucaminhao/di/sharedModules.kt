package com.dys.mobile.meucaminhao.di

import org.koin.dsl.module

val sharedModules = module {
    includes(userSettingsModule, useCaseModule, viewModelModule, dataModules)
}