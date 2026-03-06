package com.KivoFit.ui.screens.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

/**
 * ----------------------------------------------------------------------------
 * HistoryEntry.kt
 * ----------------------------------------------------------------------------
 *
 * Entry point de la pantalla Historial. Se encarga de obtener el ViewModel,
 * gestionar eventos y pasar el estado a `HistoryScreen`. Guardamos el mismo
 * patrón utilizado en otras features para mantener la consistencia.
 */

@Composable
fun HistoryEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val vm: HistoryViewModel = hiltViewModel()

    HandleNavigationEvents(navController, snackbarHostState, vm.events)

    val state by vm.state.collectAsState()

    HistoryScreen(state = state)
}
