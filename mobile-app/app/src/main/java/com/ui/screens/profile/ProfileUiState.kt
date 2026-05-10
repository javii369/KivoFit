package com.KivoFit.ui.screens.profile

data class ProfileUiState(
    val userName: String = "",
    val email: String = "",
    val membership: String = "",
    val isLoading: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val darkMode: Boolean = true
)
