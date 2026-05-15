package com.KivoFit.ui.screens.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val submitError: String? = null
) {
    val isValid: Boolean
        get() = emailError == null &&
                passwordError == null &&
                email.isNotBlank() &&
                password.isNotBlank()
}
