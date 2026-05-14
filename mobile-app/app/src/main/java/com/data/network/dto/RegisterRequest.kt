package com.KivoFit.data.network.dto

import com.squareup.moshi.Json

data class RegisterRequest(
    val dni: String,
    val nombre: String,
    val apellido: String,
    @Json(name = "segundo_apellido") val segundoApellido: String?,
    val email: String,
    val password: String,
    @Json(name = "password_confirmation") val passwordConfirmation: String,
    val rol: String
)
