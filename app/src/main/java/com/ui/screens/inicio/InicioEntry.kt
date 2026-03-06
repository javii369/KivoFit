package com.KivoFit.ui.screens.inicio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

/**
 * ----------------------------------------------------------------------------
 * InicioEntry.kt
 * ----------------------------------------------------------------------------
 *
 * Punto de entrada composable de la pantalla "Inicio". Se encarga de:
 * 1. Obtener el `InicioViewModel` inyectado por Hilt.
 * 2. Escuchar los eventos efímeros para navegación/snackbar.
 * 3. Observar el estado y pasarlo a `InicioScreen` junto con callbacks.
 *
 * Esta capa mantiene la separación entre UI pura y la lógica de presentación,
 * permitiendo escribir tests de UI sin necesidad de ViewModels reales.
 */

@Composable
fun InicioEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val vm: InicioViewModel = hiltViewModel()

    HandleNavigationEvents(navController, snackbarHostState, vm.events)

    val state by vm.state.collectAsState()

    InicioScreen(
        state = state,
        onRequestPlan = vm::onRequestPlan,
        onReserveClass = vm::onReserveClass,
        onViewRoutine = vm::onViewRoutine
    )
}
