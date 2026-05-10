package com.KivoFit.domain.usecase.auth

import com.KivoFit.domain.model.RegisterParams
import com.KivoFit.domain.repository.auth.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(params: RegisterParams): Result<Unit> =
        repo.register(params)
}
