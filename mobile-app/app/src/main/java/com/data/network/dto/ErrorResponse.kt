package com.KivoFit.data.network.dto


data class ErrorResponse(
    val message: String?,
    val errors: Map<String, List<String>>? = null,
    val error: String? = null
)
