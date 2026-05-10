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

/**
 * ----------------------------------------------------------------------------
 * HistoryViewModel.kt
 * ----------------------------------------------------------------------------
 *
 * ViewModel para la pantalla de historial. Actualmente solo expone un estado
 * inicial con el título; más adelante se podrá añadir lógica y datos reales.
 */

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(HistoryUiState())
    val state: StateFlow<HistoryUiState> = _state.asStateFlow()

    // Eventos efímeros para navegación o snackbars (por si fuera necesario)
    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()
}
