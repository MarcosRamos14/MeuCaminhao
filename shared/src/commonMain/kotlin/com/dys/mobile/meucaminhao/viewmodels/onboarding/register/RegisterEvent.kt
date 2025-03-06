package com.dys.mobile.meucaminhao.viewmodels.onboarding.register

import com.dys.mobile.meucaminhao.domain.enum.UserProfileTypeEnum

interface RegisterEvent {
    data class FullNameChanged(val fullName: String) : RegisterEvent
    data class EmailChanged(val email: String) : RegisterEvent
    data class PasswordChanged(val password: String) : RegisterEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent
    data class AcceptTermsChanged(val acceptTerms: Boolean) : RegisterEvent
    data class ProfileTypeChanged(val profileType: UserProfileTypeEnum) : RegisterEvent
    data object Advance : RegisterEvent
}