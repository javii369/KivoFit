package com.KivoFit.ui.screens.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InicioViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        InicioUiState(
            userName = "Juan",
            trainings = 24,
            calories = 8500,
            streak = 12,
            goalsCompleted = 3,
            goalsTotal = 5,
            reserveInfo = "12 clases disponibles hoy",
            routineInfo = "Día 3 de tu plan"
        )
    )
    val state: StateFlow<InicioUiState> = _state.asStateFlow()

    private val _events = kotlinx.coroutines.channels.Channel<com.KivoFit.navigation.UiEvent>(
        kotlinx.coroutines.channels.Channel.BUFFERED
    )
    val events = _events.receiveAsFlow()

    fun onRequestPlan() = viewModelScope.launch {
        _events.send(
            com.KivoFit.navigation.UiEvent.Navigate(
                route = com.KivoFit.navigation.Route.PlanForm.route
            )
        )
    }

    fun onReserveClass() = viewModelScope.launch {
        _events.send(
            com.KivoFit.navigation.UiEvent.Navigate(
                route = com.KivoFit.navigation.Route.Calendar.route,
                popUpTo = com.KivoFit.navigation.Route.Inicio.route,
                inclusive = false,
                singleTop = true
            )
        )
    }

    fun onViewRoutine() = viewModelScope.launch {
        _events.send(
            com.KivoFit.navigation.UiEvent.Navigate(
                route = com.KivoFit.navigation.Route.Routine.route
            )
        )
    }
}
