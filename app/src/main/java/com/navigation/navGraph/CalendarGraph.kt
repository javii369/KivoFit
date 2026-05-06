package com.KivoFit.navigation.navGraph

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.KivoFit.navigation.Route
import com.KivoFit.ui.screens.calendar.CalendarEntry

fun NavGraphBuilder.calendarGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    composable(Route.Calendar.route) {
        CalendarEntry(navController, snackbarHostState)
    }
}
