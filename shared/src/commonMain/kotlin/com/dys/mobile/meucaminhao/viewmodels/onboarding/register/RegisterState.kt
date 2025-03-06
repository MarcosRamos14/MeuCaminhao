package com.dys.mobile.meucaminhao.viewmodels.onboarding.register

import com.dys.mobile.meucaminhao.domain.enum.UserProfileTypeEnum
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class RegisterState(
    val fullName: String = "",
    val email: String = "",
    val emailError: CredentialsErrorState? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: CredentialsErrorState? = null,
    val acceptedTerms: Boolean = false,
    val profileType: UserProfileTypeEnum = UserProfileTypeEnum.UNDEFINED
)

fun MutableStateFlow<RegisterState>.updateFullName(fullName: String) {
    update { it.copy(fullName = fullName) }
}

fun MutableStateFlow<RegisterState>.updateEmail(email: String) {
    update { it.copy(email = email) }
}

fun MutableStateFlow<RegisterState>.updateEmailError(error: CredentialsErrorState?) {
    update { it.copy(emailError = error) }
}

fun MutableStateFlow<RegisterState>.updatePassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<RegisterState>.updateConfirmPassword(confirmPassword: String) {
    update { it.copy(confirmPassword = confirmPassword) }
}

fun MutableStateFlow<RegisterState>.updatePasswordError(error: CredentialsErrorState?) {
    update { it.copy(passwordError = error) }
}

fun MutableStateFlow<RegisterState>.updateAcceptTerms(accepted: Boolean) {
    update { it.copy(acceptedTerms = accepted) }
}

fun MutableStateFlow<RegisterState>.updateProfileType(profileType: UserProfileTypeEnum) {
    update { it.copy(profileType = profileType) }
}

// TODO: Valid terms as true
fun RegisterState.isAllFieldsFilled() : Boolean {
    return fullName.isNotEmpty() &&
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword.isNotEmpty()
}