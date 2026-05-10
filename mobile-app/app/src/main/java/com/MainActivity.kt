package com.KivoFit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.KivoFit.navigation.AppNav
import com.KivoFit.ui.theme.KivoFitTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val snackbarHostState = remember { SnackbarHostState() }

            KivoFitTheme {
                val backgroundColor = MaterialTheme.colorScheme.background
                val view = LocalView.current
                if (!view.isInEditMode) {
                    SideEffect {
                        val window = (view.context as android.app.Activity).window
                        window.statusBarColor = backgroundColor.toArgb()
                        window.navigationBarColor = backgroundColor.toArgb()
                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
                    }
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    topBar = {
                        com.KivoFit.ui.components.TopBar(
                            navController = navController,
                            onProfileClick = {
                                navController.navigate(com.KivoFit.navigation.Route.Profile.route) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    },
                    bottomBar = { com.KivoFit.ui.components.BottomBar(navController) }
                ) { padding ->
                    AppNav(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        contentPadding = padding,
                        start = com.KivoFit.navigation.Route.Inicio
                    )
                }
            }
        }
    }
}
