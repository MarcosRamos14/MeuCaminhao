package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.data.authentication.AuthenticationRepository
import com.dys.mobile.meucaminhao.data.vehicle.VehiclesRepository
import com.dys.mobile.meucaminhao.data.vehicle.VehiclesRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModules = module {
    single { AuthenticationRepository(get()) }
    singleOf<VehiclesRepository>(::VehiclesRepositoryImpl)
}