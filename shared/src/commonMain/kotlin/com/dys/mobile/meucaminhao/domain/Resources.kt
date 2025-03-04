package com.dys.mobile.meucaminhao.domain

expect object Resources {

    object Strings {
        val defaultErrorMessage: String
    }
}

val stringResources get() = Resources.Strings