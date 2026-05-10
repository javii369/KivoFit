package com.KivoFit.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

/**
 * ----------------------------------------------------------------------------
 * Theme.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Punto de entrada principal del **tema global de la aplicación**.
 *
 * Este archivo centraliza la configuración visual del proyecto:
 * colores, tipografía, formas (shapes), espaciados y elevaciones.
 *
 * Es el equivalente a una “hoja de estilos global” en Jetpack Compose.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **UI / Theme**
 * - Se aplica en el nivel raíz de la app (normalmente en MainActivity).
 * - Hace uso del sistema `MaterialTheme` de Jetpack Compose.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué es importante:
 * ----------------------------------------------------------------------------
 * ✅ Define la identidad visual de toda la app desde un solo punto.
 * ✅ Facilita el soporte para modo claro/oscuro (dark theme).
 * ✅ Permite integrar **Dynamic Colors** (Android 12+).
 * ✅ Mantiene la coherencia visual entre componentes.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * @Composable
 * fun AppRoot() {
 *     KivoFitTheme {
 *         // Dentro de este bloque ya puedes usar:
 *         // MaterialTheme.colorScheme.primary
 *         // MaterialTheme.typography.titleLarge
 *         // MaterialTheme.shapes.medium
 *         // KivoFitTheme.spacing.lg
 *     }
 * }
 * ```
 *
 * ----------------------------------------------------------------------------
 * 🔹 Parámetros:
 * ----------------------------------------------------------------------------
 * @param darkTheme: Boolean
 *     Determina si se aplica el tema oscuro. Por defecto, detecta el modo del sistema.
 *
 * @param dynamicColor: Boolean
 *     Activa los **Dynamic Colors** de Material You (Android 12+), que adaptan la
 *     paleta de colores según el fondo de pantalla del usuario.
 *
 * @param content: @Composable
 *     Contenido hijo que heredará el tema (pantallas, vistas, etc.).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Funcionamiento interno:
 * ----------------------------------------------------------------------------
 * 1️⃣ Determina si usar tema claro u oscuro.
 * 2️⃣ Aplica **Dynamic Color** si está disponible.
 * 3️⃣ Inyecta “providers” personalizados (`Spacing`, `Elevations`).
 * 4️⃣ Llama a `MaterialTheme`, integrando todos los elementos visuales.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Cómo extender el tema:
 * ----------------------------------------------------------------------------
 * Puedes añadir nuevos CompositionLocals (por ejemplo, tipografía secundaria,
 * tamaños de iconos o animaciones) y proporcionarlos desde aquí.
 * ----------------------------------------------------------------------------
 */
@Composable
fun KivoFitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // activa Dynamic Color en Android 12+
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    // 🎨 Sistema de colores: Dynamic Colors en Android 12+ o paleta fija
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    // 🧩 Inyección de valores personalizados del tema (spacing, elevations…)
    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalElevations provides Elevations()
    ) {
        // 🪄 MaterialTheme: el contenedor global que unifica todo el estilo
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}
