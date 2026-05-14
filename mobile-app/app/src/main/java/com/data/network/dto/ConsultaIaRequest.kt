package com.KivoFit.data.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConsultaIaRequest(
    @Json(name = "consulta_usuario") val consultaUsuario: String,
    @Json(name = "dieta_id") val dietaId: Int? = null,
    @Json(name = "rutina_id") val rutinaId: Int? = null
)
