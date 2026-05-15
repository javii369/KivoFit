package com.KivoFit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import com.KivoFit.navigation.navGraph.authGraph
import com.KivoFit.navigation.navGraph.inicioGraph
import com.KivoFit.navigation.navGraph.historyGraph
import com.KivoFit.navigation.navGraph.calendarGraph
import com.KivoFit.navigation.navGraph.chatGraph
import com.KivoFit.navigation.navGraph.notificationsGraph

@Composable
fun AppNav(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    start: Route = Route.Login
) {
    NavHost(
        navController = navController,
        startDestination = start.route,
        modifier = modifier.padding(contentPadding)
    ) {
        authGraph(navController, snackbarHostState)
        inicioGraph(navController, snackbarHostState)
        historyGraph(navController, snackbarHostState)
        calendarGraph(navController, snackbarHostState)
        chatGraph(navController, snackbarHostState)
        notificationsGraph(navController, snackbarHostState)
    }
}
