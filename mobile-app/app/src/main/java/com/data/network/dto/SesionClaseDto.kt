package com.KivoFit.data.network.dto

import com.squareup.moshi.Json

data class ClaseSummaryDto(
    val id: Long,
    @Json(name = "nombre_clase") val nombreClase: String,
    val nivel: String?
)

data class SesionClaseDto(
    val id: Long,
    @Json(name = "clase_id") val claseId: Long,
    val fecha: String,
    @Json(name = "hora_inicio") val horaInicio: String?,
    val duracion: Int?,
    @Json(name = "capacidad_maxima") val capacidadMaxima: Int?,
    val sala: String?,
    val clase: ClaseSummaryDto?
)
