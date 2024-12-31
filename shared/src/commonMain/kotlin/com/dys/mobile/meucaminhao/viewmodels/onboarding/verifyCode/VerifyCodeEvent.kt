package com.dys.mobile.meucaminhao.viewmodels.onboarding.verifyCode

sealed interface VerifyCodeEvent {
    data class NumberChanged(val number: Int?, val index: Int) : VerifyCodeEvent
    data class ChangeFieldFocused(val index: Int) : VerifyCodeEvent
    data object VerifyCode : VerifyCodeEvent
    data object NoCodeReceived : VerifyCodeEvent
}