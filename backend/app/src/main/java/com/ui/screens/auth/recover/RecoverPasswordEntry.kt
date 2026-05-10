package com.KivoFit.ui.screens.auth.recover

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun RecoverPasswordEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val vm: RecoverPasswordViewModel = hiltViewModel()
    HandleNavigationEvents(navController, snackbarHostState, vm.events)
    val state by vm.state.collectAsState()

    RecoverPasswordScreen(
        state = state,
        onEmailChange = vm::onEmailChange,
        onRecoverClick = vm::onRecoverClick,
        onBackClick = vm::onBackClick
    )
}
