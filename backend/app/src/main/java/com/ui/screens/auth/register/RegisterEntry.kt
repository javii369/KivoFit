package com.KivoFit.ui.screens.auth.register

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

/**
 * ----------------------------------------------------------------------------
 * RegisterEntry.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este composable actúa como **punto de entrada** (Entry Point) para la pantalla
 * de registro dentro del flujo de autenticación.
 *
 * En términos de arquitectura, su función es **conectar las tres capas**:
 *
 * ```
 * ViewModel (lógica) ⇄ RegisterEntry (conector) ⇄ RegisterScreen (UI)
 * ```
 *
 * De esta forma, se mantiene un código **modular, escalable y testable**:
 * - La UI (`RegisterScreen`) no conoce la lógica de negocio.
 * - El `ViewModel` no tiene referencias a elementos de UI (como NavController).
 * - Este Entry intermedia la comunicación entre ambos.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * ----------------------------------------------------------------------------
 * - Capa: **Presentation (UI / Navegación)**
 * - Patrón: **MVVM (Model-View-ViewModel)**
 * - Inyección de dependencias: **Hilt**
 * - Comunicación con el ViewModel:
 *   - Estado: mediante `StateFlow` (observado con `collectAsState()`).
 *   - Eventos: mediante `Channel<UiEvent>` (procesados por `HandleNavigationEvents`).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Responsabilidades principales:
 * ----------------------------------------------------------------------------
 * ✅ Inyectar el `RegisterViewModel` usando Hilt.
 * ✅ Conectar los eventos del ViewModel con el sistema de navegación y snackbar.
 * ✅ Escuchar los cambios del estado (`state`) y pasarlos a la UI.
 * ✅ Delegar las acciones del usuario (onClick, onChange, etc.) al ViewModel.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué se diseña así:
 * ----------------------------------------------------------------------------
 * - **Separación de responsabilidades**: la UI no gestiona navegación ni estados
 *   complejos, solo muestra datos.
 * - **Composición limpia**: cada pantalla tiene su propio "Entry", lo que facilita
 *   el mantenimiento del grafo de navegación.
 * - **Escalabilidad**: puedes añadir middlewares, loaders o tracking sin tocar
 *   la lógica del ViewModel ni la UI.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ciclo de ejecución típico:
 * ----------------------------------------------------------------------------
 * 1️⃣ El NavHost muestra este destino (`Route.Register`).
 * 2️⃣ Hilt crea automáticamente el `RegisterViewModel`.
 * 3️⃣ Se suscribe a los estados y eventos del ViewModel.
 * 4️⃣ Pasa el estado actual a `RegisterScreen`.
 * 5️⃣ El usuario interactúa → la UI ejecuta callbacks del ViewModel.
 * 6️⃣ El ViewModel procesa la acción y emite nuevos estados o eventos.
 * 7️⃣ `HandleNavigationEvents` recibe los eventos y navega si corresponde.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo visual del flujo:
 * ----------------------------------------------------------------------------
 * ```
 * [RegisterScreen] ─▶ onRegisterClick() ─▶ [RegisterViewModel]
 *          ▲                                       │
 *          │             UiEvent.Navigate(...)     │
 *          └────────────── HandleNavigationEvents ◀─┘
 * ```
 *
 * ----------------------------------------------------------------------------
 * 🔹 Uso típico:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * composable(Route.Register.route) {
 *     RegisterEntry(
 *         navController = navController,
 *         snackbarHostState = snackbarHostState,
 *         contentPadding = contentPadding
 *     )
 * }
 * ```
 * ----------------------------------------------------------------------------
 */
@Composable
fun RegisterEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    // 1️⃣ Inyección automática del ViewModel con Hilt
    val vm: RegisterViewModel = hiltViewModel()

    // 2️⃣ Manejo de eventos de navegación (UiEvent.Navigate, UiEvent.Snackbar, etc.)
    HandleNavigationEvents(navController, snackbarHostState, vm.events)

    // 3️⃣ Observación del estado de la UI en tiempo real (StateFlow -> Compose)
    val state by vm.state.collectAsState()

    // 4️⃣ Renderizado de la UI, delegando callbacks al ViewModel
    RegisterScreen(
        state = state,
        onEmailChange = vm::onEmailChange,
        onPasswordChange = vm::onPasswordChange,
        onRepeatChange = vm::onRepeatChange,
        onRegisterClick = vm::onRegisterClick,
        onBackClick = vm::onBackClick
    )
}
