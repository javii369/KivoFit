package com.KivoFit.navigation.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.KivoFit.navigation.Route
import com.KivoFit.ui.screens.chat.ChatEntry

fun NavGraphBuilder.chatGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    composable(Route.Chat.route) {
        ChatEntry(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }
}
