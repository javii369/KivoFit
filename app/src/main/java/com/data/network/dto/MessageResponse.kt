package com.KivoFit.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageResponse(
    val message: String
)
