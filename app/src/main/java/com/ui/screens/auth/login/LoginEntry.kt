package com.KivoFit.ui.screens.auth.login

import com.KivoFit.ui.screens.login.LoginScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.material3.SnackbarHostState
import com.KivoFit.navigation.HandleNavigationEvents

/**
 * ----------------------------------------------------------------------------
 * LoginEntry.kt
 * ----------------------------------------------------------------------------
 * Punto de entrada de la pantalla de Login:
 * - Inyecta el ViewModel con Hilt.
 * - Conecta eventos de navegación (UiEvent) con NavController/Snackbar.
 * - Recoge el estado y lo pasa a la UI pura (LoginScreen).
 *
 * Mantiene el “wiring” (VM ↔ UI ↔ Nav) fuera del grafo y de MainActivity.
 * ----------------------------------------------------------------------------
 */


@Composable
fun LoginEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    // VM con scope del destino en el NavHost
    val vm: LoginViewModel = hiltViewModel()

    // Suscribe los eventos del VM (Navigate, Snackbar, Back…)
    HandleNavigationEvents(navController, snackbarHostState, vm.events)

    // Estado de la pantalla
    // val state by vm.state.collectAsStateWithLifecycle() // ← si tienes lifecycle-runtime-compose
    val state by vm.state.collectAsState()

    LoginScreen(
        state = state,
        onEmailChange = vm::onEmailChange,
        onPasswordChange = vm::onPasswordChange,
        onLoginClick = vm::onLoginClick,
        onRegisterClick = vm::onRegisterClick,
        onRecoverClick = vm::onRecoverClick,
        // Respeta el padding que venga del Scaffold/NavHost superior
        contentPadding = contentPadding
    )
}
