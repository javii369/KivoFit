package com.KivoFit.ui.screens.routine

import androidx.lifecycle.ViewModel
import com.KivoFit.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class RoutineViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        RoutineUiState(
            workouts = listOf(
                Workout(
                    id = "1",
                    day = "Lunes",
                    name = "Empuje (pecho, hombro y tríceps)",
                    focus = "Fuerza tren superior",
                    durationMinutes = 60,
                    intensity = Intensity.High,
                    exercises = listOf(
                        Exercise("Press banca", 4, "8-10", "90 s"),
                        Exercise("Press militar mancuernas", 4, "10", "75 s"),
                        Exercise("Aperturas en polea", 3, "12", "60 s"),
                        Exercise("Elevaciones laterales", 4, "12-15", "45 s"),
                        Exercise("Fondos en paralelas", 3, "AMRAP", "60 s")
                    )
                ),
                Workout(
                    id = "2",
                    day = "Martes",
                    name = "Tracción (espalda y bíceps)",
                    focus = "Fuerza tren superior",
                    durationMinutes = 55,
                    intensity = Intensity.High,
                    exercises = listOf(
                        Exercise("Dominadas asistidas", 4, "8", "90 s"),
                        Exercise("Remo con barra", 4, "10", "75 s"),
                        Exercise("Jalón al pecho", 3, "12", "60 s"),
                        Exercise("Curl con barra Z", 3, "10-12", "60 s"),
                        Exercise("Curl martillo", 3, "12", "45 s")
                    )
                ),
                Workout(
                    id = "3",
                    day = "Miércoles",
                    name = "Cardio + core",
                    focus = "Resistencia y abdomen",
                    durationMinutes = 45,
                    intensity = Intensity.Medium,
                    exercises = listOf(
                        Exercise("Cinta en intervalos", 1, "20 min", "—"),
                        Exercise("Plancha frontal", 3, "45 s", "30 s"),
                        Exercise("Plancha lateral", 3, "30 s/lado", "30 s"),
                        Exercise("Crunch en polea", 3, "15", "45 s"),
                        Exercise("Elevación de piernas colgado", 3, "12", "60 s")
                    )
                ),
                Workout(
                    id = "4",
                    day = "Jueves",
                    name = "Pierna fuerza",
                    focus = "Cuádriceps y glúteo",
                    durationMinutes = 70,
                    intensity = Intensity.High,
                    exercises = listOf(
                        Exercise("Sentadilla con barra", 5, "5", "120 s"),
                        Exercise("Prensa inclinada", 4, "10", "90 s"),
                        Exercise("Zancadas con mancuernas", 3, "12/lado", "75 s"),
                        Exercise("Hip thrust", 4, "10", "90 s"),
                        Exercise("Gemelos de pie", 4, "15", "45 s")
                    )
                ),
                Workout(
                    id = "5",
                    day = "Viernes",
                    name = "Full body funcional",
                    focus = "Movilidad y potencia",
                    durationMinutes = 50,
                    intensity = Intensity.Medium,
                    exercises = listOf(
                        Exercise("Kettlebell swing", 4, "15", "60 s"),
                        Exercise("Goblet squat", 4, "12", "60 s"),
                        Exercise("Push press", 4, "8", "75 s"),
                        Exercise("Remo TRX", 3, "12", "45 s"),
                        Exercise("Burpees", 3, "10", "60 s")
                    )
                )
            )
        )
    )
    val state: StateFlow<RoutineUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()
}
