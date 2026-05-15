package com.KivoFit.ui.screens.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import com.KivoFit.navigation.UiEvent

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        HistoryUiState(
            yearStats = ProgressStats(
                workouts = 124,
                classes = 36,
                calories = 85000,
                minutes = 4800
            ),
            monthStats = ProgressStats(
                workouts = 14,
                classes = 4,
                calories = 9200,
                minutes = 540
            ),
            dayStats = ProgressStats(
                workouts = 1,
                classes = 1,
                calories = 620,
                minutes = 75
            ),
            activities = listOf(
                HistoryActivity(
                    id = "1",
                    kind = HistoryActivityKind.Class,
                    name = "Funcional",
                    dateLabel = "Hoy",
                    timeLabel = "18:00",
                    durationMinutes = 60,
                    calories = 480
                ),
                HistoryActivity(
                    id = "2",
                    kind = HistoryActivityKind.Workout,
                    name = "Pecho y tríceps",
                    dateLabel = "Ayer",
                    timeLabel = "19:30",
                    durationMinutes = 55,
                    calories = 410
                ),
                HistoryActivity(
                    id = "3",
                    kind = HistoryActivityKind.Class,
                    name = "Yoga",
                    dateLabel = "Mar 28 abr",
                    timeLabel = "10:00",
                    durationMinutes = 45,
                    calories = 220
                ),
                HistoryActivity(
                    id = "4",
                    kind = HistoryActivityKind.Workout,
                    name = "Cardio HIIT",
                    dateLabel = "Lun 27 abr",
                    timeLabel = "07:30",
                    durationMinutes = 30,
                    calories = 320
                )
            )
        )
    )
    val state: StateFlow<HistoryUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()
}
