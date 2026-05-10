package com.KivoFit.domain.repository.user

import com.KivoFit.domain.model.UserSummary

interface UserRepository {
    suspend fun getMe(): Result<UserSummary>
}
