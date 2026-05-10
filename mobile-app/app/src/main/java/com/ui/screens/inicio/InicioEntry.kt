package com.KivoFit.ui.screens.inicio

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun InicioEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
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
