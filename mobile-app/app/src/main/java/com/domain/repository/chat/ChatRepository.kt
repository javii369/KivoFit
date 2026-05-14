package com.KivoFit.domain.repository.chat

interface ChatRepository {
    suspend fun send(consulta: String): Result<String>
}
