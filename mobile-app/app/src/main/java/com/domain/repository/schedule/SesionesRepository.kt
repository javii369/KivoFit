package com.KivoFit.domain.repository.schedule

import com.KivoFit.domain.model.SesionBrief

interface SesionesRepository {
    suspend fun listSesiones(): Result<List<SesionBrief>>
}
