package com.dys.mobile.meucaminhao.domain.state

sealed interface CredentialsErrorState {
    data object InvalidEmail : CredentialsErrorState
    data object PasswordTooShort : CredentialsErrorState
    data object PasswordsDoNotMatch : CredentialsErrorState
    data object InvalidVerificationCode : CredentialsErrorState
}