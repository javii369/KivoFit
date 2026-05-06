package com.KivoFit.ui.screens.history

data class HistoryUiState(
    val title: String = "Historial",
    val yearStats: ProgressStats = ProgressStats(),
    val monthStats: ProgressStats = ProgressStats(),
    val dayStats: ProgressStats = ProgressStats(),
    val activities: List<HistoryActivity> = emptyList()
)

data class ProgressStats(
    val workouts: Int = 0,
    val classes: Int = 0,
    val calories: Int = 0,
    val minutes: Int = 0
)

enum class HistoryActivityKind { Workout, Class }

data class HistoryActivity(
    val id: String,
    val kind: HistoryActivityKind,
    val name: String,
    val dateLabel: String,
    val timeLabel: String,
    val durationMinutes: Int,
    val calories: Int? = null
)
