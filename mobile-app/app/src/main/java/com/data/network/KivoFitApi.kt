package com.KivoFit.data.network

import com.KivoFit.data.network.dto.AuthResponse
import com.KivoFit.data.network.dto.LoginRequest
import com.KivoFit.data.network.dto.MessageResponse
import com.KivoFit.data.network.dto.RegisterRequest
import com.KivoFit.data.network.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KivoFitApi {

    @POST("login")
    suspend fun login(@Body body: LoginRequest): AuthResponse

    @POST("register")
    suspend fun register(@Body body: RegisterRequest): AuthResponse

    @POST("logout")
    suspend fun logout(): MessageResponse

    @GET("me")
    suspend fun me(): UserDto
}
