package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.domain.usecase.EmailValidatorUseCaseImpl
import com.dys.mobile.meucaminhao.domain.usecase.PasswordValidatorUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single {
        EmailValidatorUseCaseImpl()
    }
    single {
        PasswordValidatorUseCaseImpl()
    }
}