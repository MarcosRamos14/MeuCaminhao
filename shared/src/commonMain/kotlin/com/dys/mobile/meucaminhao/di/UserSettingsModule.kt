package com.dys.mobile.meucaminhao.di

import com.dys.mobile.meucaminhao.domain.SessionManager
import com.dys.mobile.meucaminhao.domain.SessionManagerImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val userSettingsModule = module {
    singleOf<SessionManager>(::SessionManagerImpl)
}