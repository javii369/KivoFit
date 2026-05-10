package com.KivoFit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
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

/**
 * ----------------------------------------------------------------------------
 * MainActivity.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Rol de esta Activity:
 * Punto de entrada de la app. Monta el **árbol de Compose**, inicializa la
 * **navegación** y el **host de Snackbar** global, y aplica el **tema**.
 *
 * 🔹 Decisiones de diseño:
 * - **Single-Activity App**: toda la navegación ocurre dentro de esta Activity
 *   mediante Navigation-Compose (NavHost + NavController).
 * - **Scaffold** como layout raíz: provee slots comunes (snackbarHost, topBar…).
 * - **SnackbarHostState** en este nivel: una única fuente para mensajes de UI.
 * - La Activity **no** contiene lógica de negocio: solo **orquesta** y delega
 *   en AppNav (grafo) y en las pantallas (UI pura).
 *
 * 🔹 Flujo mental:
 * MainActivity → Scaffold → AppNav (NavHost) → Subgrafos → Entrypoints → Screens
 *
 * 🔹 Buenas prácticas:
 * - Mantener aquí la *infra* (tema, nav, scaffold). La lógica vive en ViewModels.
 * - Compartir un único `SnackbarHostState` para toda la app evita solapes.
 * - Pasar `contentPadding` al NavHost (o a tus pantallas) respeta insets del Scaffold.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Extensiones recomendadas (cuando toque):
 * ----------------------------------------------------------------------------
 * - Edge-to-edge con barras de sistema (WindowCompat, SystemBars).
 * - `rememberSaveable` para estado de UI que deba sobrevivir recreaciones.
 * - Analytics de navegación (observando el backstack del NavController).
 * ----------------------------------------------------------------------------
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // 🧭 Controlador de navegación (vida de composición)
            val navController = rememberNavController()

            // 🔔 Host de Snackbar compartido por toda la app
            val snackbarHostState = remember { SnackbarHostState() }

            // 🎨 Tema global (Material 3 + tus tokens de diseño)
            // for this screen, always use dark mode per design
            KivoFitTheme(darkTheme = true) {
                // force system bars to dark color so no light line appears
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

                // 🧩 Estructura base de la pantalla
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    bottomBar = { com.KivoFit.ui.components.BottomBar(navController) }
                ) { padding ->
                    // 🔗 Conecta navegación raíz con el Scaffold (paddings/insets)
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
