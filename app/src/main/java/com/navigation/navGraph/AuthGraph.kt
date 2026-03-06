package com.KivoFit.navigation.navGraph

import com.KivoFit.ui.screens.auth.login.LoginEntry
import com.KivoFit.ui.screens.auth.recover.RecoverPasswordEntry
import com.KivoFit.ui.screens.auth.register.RegisterEntry
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.KivoFit.navigation.Route

/**
 * ----------------------------------------------------------------------------
 * AuthGraph.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este archivo define el **grafo de navegación (NavGraph)** correspondiente al
 * **módulo de autenticación**: Login, Registro y Recuperar Contraseña.
 *
 * Su objetivo es **organizar las pantallas relacionadas** bajo un mismo grafo
 * lógico (`authGraph`), mejorando la escalabilidad y separación de responsabilidades.
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la capa de **navegación (navigation layer)** del proyecto.
 * - Cada *feature* o *módulo funcional* (auth, home, settings...) tiene su propio
 *   grafo de navegación, para mantener el proyecto limpio y modular.
 * - Este enfoque es habitual en apps medianas/grandes que usan **Jetpack Navigation + Compose**.
 *
 * 🔹 Ventajas de separar grafos:
 * - Permite una **arquitectura modular**: cada módulo maneja sus propias rutas.
 * - Facilita la lectura y mantenimiento del código.
 * - Hace posible reutilizar un grafo dentro de un *NavHost principal*.
 * - Encaja perfectamente con el patrón de **"Single Activity + Multiple NavGraphs"**.
 *
 * 🔹 Parámetros:
 * @param navController       Controlador de navegación principal de la app.
 * @param snackbarHostState   Referencia al snackbar compartido para mostrar mensajes globales.
 * @param contentPadding      Padding global (útil para respetar barras de sistema o Scaffold).
 *
 * 🔹 Estructura interna:
 * - Cada `composable()` declara una pantalla del flujo de autenticación.
 * - En lugar de inyectar directamente el contenido aquí, se delega en funciones
 *   `LoginEntry`, `RegisterEntry` y `RecoverPasswordEntry`, que se encargan de:
 *      1️⃣ Inyectar el ViewModel con Hilt.
 *      2️⃣ Manejar los eventos de navegación (HandleNavigationEvents).
 *      3️⃣ Renderizar la UI final (`LoginScreen`, etc.).
 *
 * 🔹 Flujo de navegación:
 * ```
 * MainNavHost
 *    └── authGraph()
 *           ├── login
 *           ├── register
 *           └── recover_password
 * ```
 *
 * 🔹 Próximos pasos:
 * - Añadir animaciones de transición entre pantallas (con `AnimatedNavHost`).
 * - Extraer rutas y argumentos comunes a un archivo `Route.kt` centralizado.
 * ----------------------------------------------------------------------------
 */
fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    contentPadding: PaddingValues
) {
    // 🧩 Pantalla de inicio de sesión
    composable(Route.Login.route) {
        LoginEntry(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }

    // 🧾 Pantalla de registro
    composable(Route.Register.route) {
        RegisterEntry(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }

    // 🔑 Pantalla de recuperación de contraseña
    composable(Route.RecoverPassword.route) {
        RecoverPasswordEntry(
            navController = navController,
            snackbarHostState = snackbarHostState,
            contentPadding = contentPadding
        )
    }
}
