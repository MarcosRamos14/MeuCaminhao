package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.viewmodels.login.LoginSharedViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword.NewPasswordViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.recoverPassword.RecoverPasswordSharedViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.register.RegisterViewModel
import com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode.VerifyCodeViewModel
import com.dys.mobile.meucaminhao.viewmodels.trips.history.TripsHistoryViewModel
import com.dys.mobile.meucaminhao.viewmodels.trips.details.TripDetailsViewModel
import com.dys.mobile.meucaminhao.viewmodels.home.HomeViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehiclesContent.VehiclesViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.myVehicles.MyVehiclesViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.newVehicle.NewVehicleViewModel
import com.dys.mobile.meucaminhao.viewmodels.vehicles.vehicleDetails.generalInfo.GeneralVehicleInfoViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::LoginSharedViewModel)
    factoryOf(::RecoverPasswordSharedViewModel)
    factoryOf(::VerifyCodeViewModel)
    factoryOf(::NewPasswordViewModel)
    factoryOf(::RegisterViewModel)
    factoryOf(::HomeViewModel)
    factoryOf(::TripsHistoryViewModel)
    factoryOf(::TripDetailsViewModel)
    factoryOf(::VehiclesViewModel)
    factoryOf(::NewVehicleViewModel)
    factoryOf(::MyVehiclesViewModel)
    factoryOf(::GeneralVehicleInfoViewModel)
}