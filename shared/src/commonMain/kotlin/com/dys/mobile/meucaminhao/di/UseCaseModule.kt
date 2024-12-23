package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<CredentialsValidatorUseCase> { CredentialsValidatorUseCaseImpl() }
}