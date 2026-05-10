package com.KivoFit.ui.screens.auth.register

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun RegisterEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val vm: RegisterViewModel = hiltViewModel()
    HandleNavigationEvents(navController, snackbarHostState, vm.events)
    val state by vm.state.collectAsState()

    RegisterScreen(
        state = state,
        onDniChange = vm::onDniChange,
        onNombreChange = vm::onNombreChange,
        onApellidoChange = vm::onApellidoChange,
        onEmailChange = vm::onEmailChange,
        onPasswordChange = vm::onPasswordChange,
        onRepeatChange = vm::onRepeatChange,
        onRegisterClick = vm::onRegisterClick,
        onBackClick = vm::onBackClick
    )
}
