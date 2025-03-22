package com.dys.mobile.meucaminhao.viewmodels.onboarding.newPassword

import com.dys.mobile.meucaminhao.domain.state.CredentialsErrorState
import com.dys.mobile.meucaminhao.domain.state.UiState
import com.dys.mobile.meucaminhao.domain.usecase.fieldValidator.CredentialsValidatorUseCase
import com.dys.mobile.meucaminhao.viewmodels.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewPasswordViewModel(
    private val credentialsValidator: CredentialsValidatorUseCase
) : BaseViewModel() {

    private var _newPasswordStateFlow = MutableStateFlow(NewPasswordState())
    val newPasswordState = _newPasswordStateFlow.asStateFlow()

    fun onEvent(event: NewPasswordEvent) {
        when (event) {
            is NewPasswordEvent.PasswordChanged -> {
                _newPasswordStateFlow.updatePassword(event.password)
            }

            is NewPasswordEvent.ConfirmPasswordChanged -> {
                _newPasswordStateFlow.updateConfirmPassword(event.confirmPassword)
            }

            NewPasswordEvent.SavePassword -> {
                validatePasswords()
            }
        }
    }

    private fun validatePasswords() {
        val newPassword = _newPasswordStateFlow.value.password
        val confirmPassword = _newPasswordStateFlow.value.confirmPassword

        if (!credentialsValidator.isPasswordValid(newPassword)) {
            _newPasswordStateFlow.updatePasswordError(CredentialsErrorState.PasswordTooShort)
            return
        }

        if (newPassword != confirmPassword) {
            _newPasswordStateFlow.updatePasswordError(CredentialsErrorState.PasswordsDoNotMatch)
            return
        }

        _newPasswordStateFlow.updatePasswordError(null)
        savePassword()
    }

    private fun savePassword() {
        // TODO: Save password. If successful, emit success state.
        emitState(UiState.Success(Unit))
    }
}