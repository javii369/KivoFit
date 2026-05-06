package com.KivoFit.navigation.navGraph

import com.KivoFit.ui.screens.auth.login.LoginEntry
import com.KivoFit.ui.screens.auth.recover.RecoverPasswordEntry
import com.KivoFit.ui.screens.auth.register.RegisterEntry
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.KivoFit.navigation.Route

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    composable(Route.Login.route) {
        LoginEntry(navController, snackbarHostState)
    }
    composable(Route.Register.route) {
        RegisterEntry(navController, snackbarHostState)
    }
    composable(Route.RecoverPassword.route) {
        RecoverPasswordEntry(navController, snackbarHostState)
    }
}
