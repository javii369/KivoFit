package com.KivoFit.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: Long,
    val dni: String,
    val nombre: String,
    val apellido: String,
    @Json(name = "segundo_apellido") val segundoApellido: String?,
    @Json(name = "foto_url") val fotoUrl: String?,
    val email: String,
    val rol: String,
    val activo: Boolean = true
)
