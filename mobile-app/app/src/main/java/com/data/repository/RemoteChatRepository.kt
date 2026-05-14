package com.KivoFit.data.repository

import com.KivoFit.data.network.ApiErrorMapper
import com.KivoFit.data.network.KivoFitApi
import com.KivoFit.data.network.dto.ConsultaIaRequest
import com.KivoFit.domain.repository.chat.ChatRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteChatRepository @Inject constructor(
    private val api: KivoFitApi
) : ChatRepository {

    override suspend fun send(consulta: String): Result<String> =
        try {
            val resp = api.enviarConsulta(ConsultaIaRequest(consultaUsuario = consulta))
            Result.success(resp.consulta.respuestaIa)
        } catch (e: HttpException) {
            Result.failure(Exception(ApiErrorMapper.message(e)))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Error de red"))
        }
}
