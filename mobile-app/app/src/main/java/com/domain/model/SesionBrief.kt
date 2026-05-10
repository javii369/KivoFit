package com.KivoFit.domain.model

/** Sesión programada vista por el cliente (datos suficientes para calendario). */
data class SesionBrief(
    val id: String,
    val nombreClase: String,
    /** Fecha yyyy-MM-dd */ val fechaIso: String,
    val horaInicio: String?,
    val duracionMinutos: Int
)
