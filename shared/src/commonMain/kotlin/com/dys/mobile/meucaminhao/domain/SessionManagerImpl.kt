package com.dys.mobile.meucaminhao.domain

import com.dys.mobile.meucaminhao.domain.dto.CredentialsDTO
import com.dys.mobile.meucaminhao.domain.dto.UserDTO

internal class SessionManagerImpl : SessionManager {

    override var currentUser: UserDTO? = null

    override fun updateCredentials(credentials: CredentialsDTO) {
        println(credentials)
    }

    override fun getCredentials(): CredentialsDTO? {
        return null //todo
    }

    override fun hasPermission() {
    }
}