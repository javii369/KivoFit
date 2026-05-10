package com.KivoFit.domain.repository.auth

import com.KivoFit.domain.model.RegisterParams

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    suspend fun recoverPassword(email: String): Result<Unit>
    suspend fun register(params: RegisterParams): Result<Unit>
}
