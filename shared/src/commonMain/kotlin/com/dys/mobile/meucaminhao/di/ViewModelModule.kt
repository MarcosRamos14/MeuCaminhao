package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.viewmodels.login.LoginSharedViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        LoginSharedViewModel(
            emailValidator = get(),
            passwordValidator = get()
        )
    }
}