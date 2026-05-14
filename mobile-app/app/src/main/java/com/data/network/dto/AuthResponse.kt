package com.KivoFit.data.network.dto


data class AuthResponse(
    val message: String?,
    val user: UserDto,
    val token: String
)
