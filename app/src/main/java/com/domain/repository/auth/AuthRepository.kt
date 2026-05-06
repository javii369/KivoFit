package com.KivoFit.domain.repository.auth

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun recoverPassword(email: String): Result<Unit>
    suspend fun register(email: String, password: String): Result<Unit>
}
