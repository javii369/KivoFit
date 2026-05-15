package com.KivoFit.ui.screens.inicio

data class InicioUiState(
    val userName: String = "Juan",
    val trainings: Int = 0,
    val calories: Int = 0,
    val streak: Int = 0,
    val goalsCompleted: Int = 0,
    val goalsTotal: Int = 0,
    val reserveInfo: String = "",
    val routineInfo: String = ""
)
