package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.data.authentication.AuthenticationRepository
import org.koin.dsl.module

val dataModules = module {
    single { AuthenticationRepository(get()) }
}