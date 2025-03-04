package com.dys.mobile.meucaminhao.domain


enum class UserProfileEnum {
    SUPER_ALPHA,
    ALPHA_TEST,
    ALPHA,
    SLEEPING_ALPHA,
    BETA;

    companion object {
        fun from(value: String?): UserProfileEnum?
            = UserProfileEnum.entries.associateBy(UserProfileEnum::name)[value]
    }
}