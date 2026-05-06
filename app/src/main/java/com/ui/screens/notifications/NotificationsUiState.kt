package com.KivoFit.ui.screens.notifications

data class NotificationsUiState(
    val title: String = "Avisos",
    val notices: List<GymNotice> = emptyList()
)

enum class NoticeKind { Closure, ReducedHours, Event, Maintenance, Info }

data class GymNotice(
    val id: String,
    val kind: NoticeKind,
    val title: String,
    val message: String,
    val dateLabel: String
)
