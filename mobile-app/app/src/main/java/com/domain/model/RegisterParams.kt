package com.KivoFit.domain.model

/**
 * Solicitud de registro alineada con el contrato POST /register del backend.
 */
data class RegisterParams(
    val dni: String,
    val nombre: String,
    val apellido: String,
    val segundoApellido: String? = null,
    val email: String,
    val password: String,
    val rol: String = "cliente"
)
