package com.dys.mobile.meucaminhao.domain.dto

import com.dys.mobile.meucaminhao.domain.UserProfileEnum
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val name: String,
    val email: String,
    val password: String,
    val cnh: String?,
    val cnhCategory: String?,
    val profilePicture: String?,
    val document: String,
    val userProfile: String?,
    val phone: String?,
)

val UserDTO.userProfileEnum: UserProfileEnum?
    get() = UserProfileEnum.from(userProfile)
