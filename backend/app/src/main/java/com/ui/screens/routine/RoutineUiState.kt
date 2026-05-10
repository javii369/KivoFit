package com.KivoFit.ui.screens.routine

data class RoutineUiState(
    val title: String = "Mi Rutina",
    val subtitle: String = "Plan semanal de 5 días diseñado a tu medida",
    val workouts: List<Workout> = emptyList()
)

data class Workout(
    val id: String,
    val day: String,
    val name: String,
    val focus: String,
    val durationMinutes: Int,
    val intensity: Intensity,
    val exercises: List<Exercise>
)

enum class Intensity(val label: String) {
    Low("Baja"), Medium("Media"), High("Alta")
}

data class Exercise(
    val name: String,
    val sets: Int,
    val reps: String,
    val rest: String
)
