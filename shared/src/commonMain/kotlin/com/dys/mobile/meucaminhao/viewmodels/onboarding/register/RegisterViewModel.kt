package com.dys.mobile.meucaminhao.viewmodels.onboarding.register

import com.dys.mobile.meucaminhao.domain.enum.UserProfileTypeEnum
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.InvalidEmail
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.PasswordTooShort
import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState.PasswordsDoNotMatch
import com.dys.mobile.meucaminhao.domain.state.asSingleEvent
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.navigation.routes.Routes
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel(
    private val credentialsValidator: CredentialsValidatorUseCase
) : BaseViewModel() {

    private var _registerStateFlow = MutableStateFlow(RegisterState())
    val registerState = _registerStateFlow.asStateFlow()

    fun onCreateAccountEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.FullNameChanged -> {
                _registerStateFlow.updateFullName(event.fullName)
            }

            is RegisterEvent.EmailChanged -> {
                _registerStateFlow.updateEmail(event.email)
            }

            is RegisterEvent.PasswordChanged -> {
                _registerStateFlow.updatePassword(event.password)
            }

            is RegisterEvent.ConfirmPasswordChanged -> {
                _registerStateFlow.updateConfirmPassword(event.confirmPassword)
            }

            is RegisterEvent.AcceptTermsChanged -> {
                //TODO: _registerStateFlow.updateAcceptTerms(event.acceptTerms)
            }

            RegisterEvent.Advance -> {
                validateCredentials()
            }
        }
    }

    fun onProfileTypeEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.ProfileTypeChanged -> {
                _registerStateFlow.updateProfileType(event.profileType)
            }

            RegisterEvent.Advance -> {
                checkProfileType()
            }
        }
    }

    fun onPlanTypeEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.ProfileTypeChanged -> {
                _registerStateFlow.updateProfileType(event.profileType)
            }

            RegisterEvent.Advance -> {
                //TODO:
            }
        }
    }

    fun resetState() {
        _registerStateFlow.value = RegisterState()
    }

    private fun validateCredentials() {
        val state = _registerStateFlow.value
        val emailError = if (!credentialsValidator.isEmailValid(state.email)) InvalidEmail else null
        val passwordError = when {
            !credentialsValidator.isPasswordValid(state.password) -> PasswordTooShort
            state.password != state.confirmPassword -> PasswordsDoNotMatch
            else -> null
        }

        _registerStateFlow.updateEmailError(emailError)
        _registerStateFlow.updatePasswordError(passwordError)

        if (emailError == null && passwordError == null) {
            emitNavigateEvent(Routes.ProfileTypeScreen.route)
        }
    }

    private fun checkProfileType() {
        val route = when (_registerStateFlow.value.profileType) {
            UserProfileTypeEnum.ALPHA -> Routes.PlanTypeScreen.route
            else -> Routes.HomeScreen.route
        }

        emitNavigateEvent(route)
    }

    private fun emitNavigateEvent(route: String) {
        updateState { state ->
            state.copy(navigation = route.asSingleEvent())
        }
    }
}