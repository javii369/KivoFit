package com.KivoFit.data.repository

import com.KivoFit.data.network.ApiErrorMapper
import com.KivoFit.data.network.KivoFitApi
import com.KivoFit.domain.model.SesionBrief
import com.KivoFit.domain.repository.schedule.SesionesRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSesionesRepository @Inject constructor(
    private val api: KivoFitApi
) : SesionesRepository {

    override suspend fun listSesiones(): Result<List<SesionBrief>> =
        try {
            val raw = api.sesiones()
            Result.success(
                raw.map { s ->
                    val nombre = s.clase?.nombreClase ?: "Clase"
                    SesionBrief(
                        id = s.id.toString(),
                        nombreClase = nombre,
                        fechaIso = s.fecha,
                        horaInicio = s.horaInicio,
                        duracionMinutos = s.duracion?.takeIf { it > 0 } ?: 60
                    )
                }
            )
        } catch (e: HttpException) {
            Result.failure(Exception(ApiErrorMapper.message(e)))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Error de red"))
        }
}
