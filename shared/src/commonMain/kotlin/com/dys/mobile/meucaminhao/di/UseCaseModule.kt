package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.EmailValidatorUseCaseImpl
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.PasswordValidatorUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::EmailValidatorUseCaseImpl)
    singleOf(::PasswordValidatorUseCaseImpl)
}