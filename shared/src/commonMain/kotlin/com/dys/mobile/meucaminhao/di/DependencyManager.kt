package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.data.authentication.AuthenticationRepository

interface DependencyManager {
    fun authenticationRepository(): AuthenticationRepository
}