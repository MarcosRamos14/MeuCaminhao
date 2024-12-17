package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.viewmodels.login.LoginSharedViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordSharedViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    singleOf(::LoginSharedViewModel)
    singleOf(::RecoverPasswordSharedViewModel)
}