package com.dys.mobile.meucaminhao.domain.enum

enum class UserProfileTypeEnum {
    UNDEFINED,

    /**
     * Special Users
     */
    SUPER_ALPHA,

    /**
     * User in test period
     */
    ALPHA_TEST,

    /**
     * User that pay for it
     */
    ALPHA,

    /**
     * It was already alpha but did not renew the subscription
     */
    SLEEPING_ALPHA,

    /**
     * Driver
     */
    BETA
}