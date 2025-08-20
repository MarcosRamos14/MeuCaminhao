package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCaseImpl
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.VehicleValidatorUseCase
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.VehicleValidatorUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    single<CredentialsValidatorUseCase> { CredentialsValidatorUseCaseImpl() }
    singleOf<VehicleValidatorUseCase>(::VehicleValidatorUseCaseImpl)
}