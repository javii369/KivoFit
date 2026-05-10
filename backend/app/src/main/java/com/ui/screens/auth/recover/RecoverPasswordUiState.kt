package com.KivoFit.ui.screens.auth.recover

data class RecoverPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val submitError: String? = null
) {
    val isValid: Boolean
        get() = emailError == null && email.isNotBlank()
}