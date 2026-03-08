package com.KivoFit.ui.screens.history

/**
 * ----------------------------------------------------------------------------
 * HistoryUiState.kt
 * ----------------------------------------------------------------------------
 *
 * Estado de la pantalla "Historial". Siguiendo el patrón de `HomeUiState`, aquí
 * mantenemos únicamente un título: es un placeholder que puede expandirse más
 * tarde.
 */

data class HistoryUiState(
    val title: String = "Tu Historial",
    val subtitle: String = "Seguimiento de tu progreso",
    val trainings: String = "5",
    val calories: String = "1.9K",
    val totalTime: String = "4h",
    val weekDays: List<String> = listOf("L", "M", "X", "J", "V", "S", "D"),
    val recentWorkouts: List<String> = listOf(
        
        )
)
