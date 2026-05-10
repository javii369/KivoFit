package com.KivoFit.data.repository

import com.KivoFit.data.network.ApiErrorMapper
import com.KivoFit.data.network.KivoFitApi
import com.KivoFit.data.local.TokenStore
import com.KivoFit.data.network.dto.LoginRequest
import com.KivoFit.data.network.dto.RegisterRequest
import com.KivoFit.domain.model.RegisterParams
import com.KivoFit.domain.repository.auth.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteAuthRepository @Inject constructor(
    private val api: KivoFitApi,
    private val tokenStore: TokenStore
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> =
        try {
            val resp = api.login(LoginRequest(email = email, password = password))
            val u = resp.user
            tokenStore.save(
                token = resp.token,
                role = u.rol,
                email = u.email,
                name = "${u.nombre.trim()} ${u.apellido.trim()}".trim()
            )
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(Exception(ApiErrorMapper.message(e)))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Error de red"))
        }

    override suspend fun logout(): Result<Unit> =
        try {
            api.logout()
            tokenStore.clear()
            Result.success(Unit)
        } catch (_: HttpException) {
            tokenStore.clear()
            Result.success(Unit)
        } catch (e: Exception) {
            tokenStore.clear()
            Result.failure(Exception(e.message ?: "Error cerrando sesión"))
        }

    override suspend fun register(params: RegisterParams): Result<Unit> =
        try {
            api.register(
                RegisterRequest(
                    dni = params.dni.trim(),
                    nombre = params.nombre.trim(),
                    apellido = params.apellido.trim(),
                    segundoApellido = params.segundoApellido?.trim()?.takeIf { it.isNotEmpty() },
                    email = params.email.trim(),
                    password = params.password,
                    passwordConfirmation = params.password,
                    rol = params.rol
                )
            )
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(Exception(ApiErrorMapper.message(e)))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Error de red"))
        }

    override suspend fun recoverPassword(email: String): Result<Unit> =
        Result.failure(
            Exception("El servidor aún no expone recuperación de contraseña por API")
        )
}
