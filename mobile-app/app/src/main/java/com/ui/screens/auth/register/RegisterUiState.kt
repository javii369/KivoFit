package com.KivoFit.ui.screens.auth.register

data class RegisterUiState(
    val dni: String = "",
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val password: String = "",
    val repeat: String = "",
    val isLoading: Boolean = false,
    val dniError: String? = null,
    val nombreError: String? = null,
    val apellidoError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatError: String? = null,
    val submitError: String? = null
) {
    val isValid: Boolean
        get() = listOf(dniError, nombreError, apellidoError, emailError, passwordError, repeatError)
            .all { it == null } &&
                listOf(dni, nombre, apellido, email, password, repeat).all { it.isNotBlank() }
}
