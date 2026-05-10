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

/**
 * ----------------------------------------------------------------------------
 * InicioViewModel.kt
 * ----------------------------------------------------------------------------
 *
 * ViewModel asociado a `InicioScreen`. Su objetivo es ofrecer los datos que
 * aparecen en la pantalla principal verdadera (saludo, estadísticas, tarjetas
 * rápidas, etc.). Al tratarse de un ejemplo, los valores se rellenan con datos
 * de prueba estáticos; en una aplicación real vendrían de repositorios o de
 * casos de uso específicos.
 *
 * El patrón es idéntico al de `HomeViewModel`:
 * 1. Mantener un `MutableStateFlow` con el estado de UI.
 * 2. Exponerlo como `StateFlow` inmutable para la UI.
 * 3. (Opcional) emitir eventos efímeros para navegación/snackbar.
 *
 * A modo de demostración también incluimos callbacks simples para los botones
 * de la interfaz, que simplemente emiten `UiEvent`s de navegación.
 */
@HiltViewModel
class InicioViewModel @Inject constructor() : ViewModel() {

    // Estado observable por la UI
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

    // Eventos efímeros para navegación/snackbar (no estrictamente necesarios)
    private val _events = kotlinx.coroutines.channels.Channel<com.KivoFit.navigation.UiEvent>(
        kotlinx.coroutines.channels.Channel.BUFFERED
    )
    val events = _events.receiveAsFlow()

    //--------------------------------------------
    // Acciones públicas invocadas desde la UI
    //--------------------------------------------

    fun onRequestPlan() = viewModelScope.launch {
        _events.send(
            com.KivoFit.navigation.UiEvent.ShowSnackbar("Plan solicitado")
        )
    }

    fun onReserveClass() = viewModelScope.launch {
        _events.send(
            com.KivoFit.navigation.UiEvent.ShowSnackbar("Reservar clase")
        )
    }

    fun onViewRoutine() = viewModelScope.launch {
        _events.send(
            com.KivoFit.navigation.UiEvent.ShowSnackbar("Mi rutina")
        )
    }
}
