package com.KivoFit.data.repository

import com.KivoFit.domain.repository.auth.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthRepository @Inject constructor() : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (email == "admin@ceac.com" && password == "1234") {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Credenciales incorrectas"))
        }
    }

    override suspend fun recoverPassword(email: String): Result<Unit> {
        delay(800)
        return if (email.endsWith("@ceac.com")) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("No existe ninguna cuenta con ese email"))
        }
    }

    override suspend fun register(email: String, password: String): Result<Unit> {
        delay(800)
        return when {
            !email.endsWith("@ceac.com") ->
                Result.failure(Exception("Solo aceptamos emails @ceac.com en el mock"))
            password.length < 6 ->
                Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
            else -> Result.success(Unit)
        }
    }
}
