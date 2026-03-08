package com.KivoFit.ui.screens.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun CalendarEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val vm: CalendarViewModel = hiltViewModel()

    HandleNavigationEvents(navController, snackbarHostState, vm.events)

    val state by vm.state.collectAsState()

    CalendarScreen(
        state = state,
        contentPadding = contentPadding
    )
}
