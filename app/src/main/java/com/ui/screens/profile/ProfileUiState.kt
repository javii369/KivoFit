package com.KivoFit.ui.screens.profile

data class ProfileUiState(
    val userName: String = "Juan García",
    val email: String = "juan@kivofit.com",
    val membership: String = "Plan Premium",
    val notificationsEnabled: Boolean = true,
    val darkMode: Boolean = true
)
