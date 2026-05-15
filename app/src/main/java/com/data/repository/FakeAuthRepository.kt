package com.KivoFit.data.repository

import com.KivoFit.data.local.TokenStore
import com.KivoFit.domain.repository.auth.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthRepository @Inject constructor(
    private val tokenStore: TokenStore
) : AuthRepository {

    private data class FakeUser(
        val email: String,
        val password: String,
        val role: String,
        val name: String
    )

    private val users = listOf(
        FakeUser("admin@ceac.com", "1234", "admin", "Admin"),
        FakeUser("entrenador@ceac.com", "1234", "entrenador", "Entrenador")
    )

    override suspend fun login(email: String, password: String): Result<Unit> {
        delay(400)
        val user = users.firstOrNull { it.email.equals(email, ignoreCase = true) && it.password == password }
        return if (user != null) {
            tokenStore.save(
                token = "fake-token-${user.role}",
                role = user.role,
                email = user.email,
                name = user.name
            )
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
