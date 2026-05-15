package com.KivoFit.ui.screens.routine

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun RoutineEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val vm: RoutineViewModel = hiltViewModel()
    HandleNavigationEvents(navController, snackbarHostState, vm.events)
    val state by vm.state.collectAsState()
    RoutineScreen(state = state)
}
