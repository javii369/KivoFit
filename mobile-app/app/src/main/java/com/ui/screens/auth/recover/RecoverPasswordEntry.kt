package com.KivoFit.ui.screens.auth.recover

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun RecoverPasswordEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
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
