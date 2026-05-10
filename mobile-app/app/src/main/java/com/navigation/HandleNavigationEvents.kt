package com.KivoFit.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.Flow

/**
 * ----------------------------------------------------------------------------
 * HandleNavigationEvents.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este composable actúa como **intérprete de los eventos de UI** emitidos
 * por los ViewModels. Escucha un flujo (`Flow<UiEvent>`) y ejecuta las
 * acciones correspondientes (navegar, mostrar mensajes, volver atrás, etc.).
 *
 * 🔹 Motivación:
 * - Desacopla la lógica de negocio (ViewModel) del sistema de navegación (`NavController`).
 * - Permite que la UI reaccione de forma declarativa ante los eventos sin romper
 *   los principios de arquitectura limpia.
 *
 * 🔹 Flujo típico:
 * ```
 * ViewModel → _events.send(UiEvent.Navigate(Route.Home.route))
 * UI → HandleNavigationEvents(...) → NavController.navigate("home")
 * ```
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la capa **presentation/navigation**.
 * - Se ejecuta dentro del árbol Compose (normalmente al inicio del Entry).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Uso en un Entry:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * val vm: LoginViewModel = hiltViewModel()
 * HandleNavigationEvents(navController, snackbarHostState, vm.events)
 * ```
 * ----------------------------------------------------------------------------
 */
@Composable
fun HandleNavigationEvents(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    events: Flow<UiEvent>
) {
    LaunchedEffect(Unit) {
        // Escucha continua del flujo de eventos emitidos desde el ViewModel.
        events.collect { event ->
            when (event) {

                // 🔹 Navegación hacia otra pantalla
                is UiEvent.Navigate -> {
                    navController.navigate(event.route) {
                        launchSingleTop = event.singleTop
                        event.popUpTo?.let { target ->
                            popUpTo(target) { inclusive = event.inclusive }
                        }
                    }
                }

                // 🔹 Volver atrás en el stack
                is UiEvent.NavigateBack -> navController.popBackStack()

                // 🔹 Mostrar mensaje en la interfaz
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }
}
