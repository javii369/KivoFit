package com.KivoFit.ui.screens.calendar

data class CalendarUiState(
    val title: String = "Calendario",
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val classes: List<CalendarClass> = emptyList()
)

data class CalendarClass(
    val id: String,
    val name: String,
    val dateLabel: String,
    val timeLabel: String,
    val durationMinutes: Int
)
