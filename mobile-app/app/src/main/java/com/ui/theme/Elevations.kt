package com.KivoFit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * ----------------------------------------------------------------------------
 * Elevations.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Define el **sistema de elevaciones** (niveles de profundidad o sombra)
 * usado en toda la aplicación, siguiendo las guías de **Material Design 3**.
 *
 * En Jetpack Compose, la “elevación” representa cómo de “elevado” está un
 * componente sobre la superficie base (afecta a sombras y jerarquía visual).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **UI / Theme**
 * - Se integra con `KivoFitTheme` para que esté disponible en toda la app.
 * - Acceso global mediante `KivoFitThemeExt.elevations`.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué es importante:
 * ----------------------------------------------------------------------------
 * ✅ Aporta coherencia visual en toda la interfaz.
 * ✅ Permite cambiar o ajustar todas las sombras desde un único punto.
 * ✅ Facilita el diseño adaptativo entre modo claro y oscuro.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * Card(
 *     elevation = CardDefaults.cardElevation(
 *         KivoFitThemeExt.elevations.level2
 *     )
 * ) {
 *     Text("Contenido elevado")
 * }
 * ```
 *
 * ----------------------------------------------------------------------------
 * 🔹 Guía de niveles recomendada (según Material 3):
 * ----------------------------------------------------------------------------
 * level0 → Sin sombra, fondo plano (Surface, Background)
 * level1 → Botones, campos de texto
 * level2 → Cards, Popups ligeros
 * level3 → Menús flotantes, Diálogos
 * level4 → Elementos de prioridad alta o “sticky”
 *
 * ----------------------------------------------------------------------------
 */

@Immutable
data class Elevations(
    val level0: androidx.compose.ui.unit.Dp = 0.dp,
    val level1: androidx.compose.ui.unit.Dp = 1.dp,
    val level2: androidx.compose.ui.unit.Dp = 3.dp,
    val level3: androidx.compose.ui.unit.Dp = 6.dp,
    val level4: androidx.compose.ui.unit.Dp = 12.dp
)

// 🧩 CompositionLocal: permite acceder a las elevaciones desde cualquier Composable
val LocalElevations = staticCompositionLocalOf { Elevations() }

//
// 💡 Cómo usar en la app:
// -----------------------------------------------------------------------------
// KivoFitThemeExt.elevations.level2 → obtiene el valor definido en el tema actual
//
val KivoFitElevations: @Composable () -> Elevations
    get() = { LocalElevations.current }

//
// 🚀 Interfaz pública de acceso global a las elevaciones.
// Ideal para mantener un naming consistente.
//
object KivoFitThemeExt {
    val elevations: Elevations
        @Composable get() = LocalElevations.current
}
