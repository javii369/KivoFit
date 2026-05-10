package com.KivoFit.ui.screens.notifications

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun NotificationsEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    val vm: NotificationsViewModel = hiltViewModel()

    HandleNavigationEvents(navController, snackbarHostState, vm.events)

    val state by vm.state.collectAsState()

    NotificationsScreen(state = state)
}
