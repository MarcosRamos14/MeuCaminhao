package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.data.authentication.AuthenticationRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

fun dependencyManager(): DependencyManager = DependencyManagerImpl()

internal class DependencyManagerImpl : KoinComponent, DependencyManager {
    override fun authenticationRepository(): AuthenticationRepository = get()
}
