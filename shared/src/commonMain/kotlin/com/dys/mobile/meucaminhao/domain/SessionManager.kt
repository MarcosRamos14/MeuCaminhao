package com.dys.mobile.meucaminhao.domain

import com.dys.mobile.meucaminhao.domain.dto.CredentialsDTO
import com.dys.mobile.meucaminhao.domain.dto.UserDTO

interface SessionManager {
    var currentUser: UserDTO?
    fun updateCredentials(credentials: CredentialsDTO)
    fun getCredentials(): CredentialsDTO?
    fun hasPermission()
}