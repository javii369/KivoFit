package com.KivoFit.data.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    val message: String?,
    val user: UserDto,
    val token: String
)
