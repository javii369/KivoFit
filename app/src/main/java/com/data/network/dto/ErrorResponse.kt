package com.KivoFit.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val message: String?,
    val errors: Map<String, List<String>>? = null,
    val error: String? = null
)
