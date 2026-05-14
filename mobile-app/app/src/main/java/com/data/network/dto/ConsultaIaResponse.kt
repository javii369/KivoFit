package com.KivoFit.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConsultaIaResponse(
    val message: String,
    val consulta: ConsultaIaDto
)

@JsonClass(generateAdapter = true)
data class ConsultaIaDto(
    val id: Long,
    @Json(name = "user_id") val userId: Long,
    @Json(name = "consulta_usuario") val consultaUsuario: String,
    @Json(name = "respuesta_ia") val respuestaIa: String,
    @Json(name = "created_at") val createdAt: String?
)
