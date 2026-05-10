package com.KivoFit.navigation.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.KivoFit.navigation.Route
import com.KivoFit.ui.screens.inicio.InicioEntry

/**
 * ----------------------------------------------------------------------------
 * InicioGraph.kt
 * ----------------------------------------------------------------------------
 *
 * Define el subgrafo de navegación para la feature "Inicio", que contiene la
 * pantalla principal que ve el usuario tras iniciar sesión. Su estructura es
 * idéntica a la de `HomeGraph` pero enlaza con `Route.Inicio`.
 *
 * Seguir este patrón facilita añadir nuevas rutas relacionadas con el módulo
 * (ej. detalle de plan, estadísticas ampliadas, etc.) en el futuro.
 */
fun NavGraphBuilder.inicioGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    composable(Route.Inicio.route) {
        InicioEntry(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }
}
