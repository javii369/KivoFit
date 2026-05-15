package com.KivoFit.navigation.navGraph

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.KivoFit.navigation.Route
import com.KivoFit.ui.screens.inicio.InicioEntry
import com.KivoFit.ui.screens.plan.PlanFormEntry
import com.KivoFit.ui.screens.profile.ProfileEntry
import com.KivoFit.ui.screens.routine.RoutineEntry

fun NavGraphBuilder.inicioGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    composable(Route.Inicio.route) {
        InicioEntry(navController, snackbarHostState)
    }
    composable(Route.Profile.route) {
        ProfileEntry(navController, snackbarHostState)
    }
    composable(Route.PlanForm.route) {
        PlanFormEntry(navController, snackbarHostState)
    }
    composable(Route.Routine.route) {
        RoutineEntry(navController, snackbarHostState)
    }
}
