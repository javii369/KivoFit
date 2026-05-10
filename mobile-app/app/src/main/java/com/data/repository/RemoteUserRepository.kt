package com.KivoFit.data.repository

import com.KivoFit.data.network.ApiErrorMapper
import com.KivoFit.data.network.KivoFitApi
import com.KivoFit.domain.model.UserSummary
import com.KivoFit.domain.repository.user.UserRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteUserRepository @Inject constructor(
    private val api: KivoFitApi
) : UserRepository {

    override suspend fun getMe(): Result<UserSummary> =
        try {
            val u = api.me()
            Result.success(
                UserSummary(
                    nombre = u.nombre,
                    apellido = u.apellido,
                    email = u.email,
                    rol = u.rol
                )
            )
        } catch (e: HttpException) {
            Result.failure(Exception(ApiErrorMapper.message(e)))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Error de red"))
        }
}
