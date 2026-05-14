package com.KivoFit.ui.screens.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.KivoFit.navigation.Route
import com.KivoFit.navigation.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        InicioUiState(
            userName = "",
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

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadUserName()
    }

    private fun loadUserName() = viewModelScope.launch {
        userRepository.getMe()
            .onSuccess { u ->
                val firstName = u.nombre.trim().ifBlank { "Usuario" }
                _state.update { it.copy(userName = firstName) }
            }
    }

    fun onRequestPlan() = viewModelScope.launch {
        _events.send(UiEvent.Navigate(route = Route.PlanForm.route))
    }

    fun onReserveClass() = viewModelScope.launch {
        _events.send(
            UiEvent.Navigate(
                route = Route.Calendar.route,
                popUpTo = Route.Inicio.route,
                inclusive = false,
                singleTop = true
            )
        )
    }

    fun onViewRoutine() = viewModelScope.launch {
        _events.send(UiEvent.Navigate(route = Route.Routine.route))
    }
}
