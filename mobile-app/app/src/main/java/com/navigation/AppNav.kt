package com.KivoFit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import com.KivoFit.navigation.navGraph.authGraph
import com.KivoFit.navigation.navGraph.inicioGraph
import com.KivoFit.navigation.navGraph.historyGraph
import com.KivoFit.navigation.navGraph.calendarGraph
import com.KivoFit.navigation.navGraph.chatGraph
import com.KivoFit.navigation.navGraph.notificationsGraph

/**
 * ----------------------------------------------------------------------------
 * AppNav.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Composable **raíz de navegación** que configura el `NavHost` y registra
 * los **subgrafos por feature** (auth, home, etc.). Se ejecuta desde `MainActivity`
 * y orquesta cómo se mueven las pantallas dentro de la app.
 *
 * 🔹 Decisiones de arquitectura:
 * - **NavHost único** en la Activity (patrón *Single-Activity*).
 * - **Subgrafos por feature** (`authGraph`, `inicioGraph`) para escalar y mantener limpio.
 * - Estado de UI transversal **inyectado** (p. ej. `SnackbarHostState`) desde arriba.
 * - `contentPadding` propagado para respetar paddings del `Scaffold` (insets, barras, etc.).
 *
 * 🔹 Por qué no metemos UI aquí:
 * Este nivel solo **declara el grafo**. El *wiring* (VM + eventos + pantalla) vive
 * en los `Entry` de cada feature. Resultado: `MainActivity` minimalista,
 * navegación modular y pantallas fácilmente testeables.
 *
 * 🔹 Parámetros:
 * @param navController       Controlador de navegación (back stack, navigate, etc.).
 * @param snackbarHostState   Snackbar global del `Scaffold` de la Activity.
 * @param contentPadding      Padding del contenedor superior (status, nav bar, etc.).
 * @param modifier            Modificador para el contenedor del `NavHost` si se requiere.
 * @param start               Ruta de inicio (login por defecto; útil para onboarding/sesión).
 *
 * 🔹 Flujo típico:
 * MainActivity → AppNav → (authGraph | inicioGraph) → *Entry* de cada pantalla → UI
 *
 * 🔹 Cómo añadir una feature nueva:
 * 1) Crea `navigation/navGraph/<Feature>Graph.kt` con su extensión `NavGraphBuilder.<feature>Graph(...)`.
 * 2) Crea sus `Entry` (`<Pantalla>Entry.kt`) que hagan el wiring VM/UI.
 * 3) Registra el subgrafo aquí (como `settingsGraph(...)`).
 * 4) Define su(s) ruta(s) en `Route.kt`.
 *
 * ----------------------------------------------------------------------------
 */
@Composable
fun AppNav(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    start: Route = Route.Login
) {
    // El NavHost es el “contenedor” del grafo. Aquí indicamos la ruta inicial y
    // añadimos los subgrafos de cada feature.
    NavHost(
        navController = navController,
        startDestination = start.route,
        modifier = modifier
    ) {
        // 🔐 Subgrafo de autenticación (Login, Register, Recover)
        // Se le pasa `snackbarHostState` y `contentPadding` para que cada Entry
        // pueda mostrar snackbars y respetar el layout del Scaffold.
        authGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )

        // � Subgrafo de Inicio (pantalla principal tras el login)
        inicioGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )

        // Subgrafos de cada destino de la barra inferior
        historyGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
        calendarGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
        chatGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
        notificationsGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }
}
