package com.KivoFit.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * ----------------------------------------------------------------------------
 * Spacing.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Define el **sistema de espaciados homogéneo** para toda la aplicación.
 *
 * Este archivo establece valores estándar para paddings, margins y separaciones
 * visuales entre componentes, garantizando consistencia y armonía en el diseño.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **UI / Theme**
 * - Acceso global mediante `KivoFitTheme.spacing`
 * - Se integra automáticamente dentro del `KivoFitTheme` (Theme.kt)
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué es importante:
 * ----------------------------------------------------------------------------
 * ✅ Evita usar “valores mágicos” de `dp` en el código.
 * ✅ Permite mantener coherencia visual en toda la interfaz.
 * ✅ Facilita el escalado del diseño (por ejemplo, en tablets o web).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * Column(
 *     modifier = Modifier.padding(KivoFitTheme.spacing.lg)
 * ) {
 *     Text("Hola mundo")
 *     Spacer(modifier = Modifier.height(KivoFitTheme.spacing.md))
 *     Button(onClick = {}) { Text("Aceptar") }
 * }
 * ```
 *
 * ----------------------------------------------------------------------------
 * 🔹 Escala de referencia visual:
 * ----------------------------------------------------------------------------
 * | Tamaño | Valor | Uso recomendado |
 * |---------|--------|----------------|
 * | `xs` | 4.dp | Espaciado mínimo entre iconos/textos |
 * | `sm` | 8.dp | Espaciado entre elementos pequeños |
 * | `md` | 12.dp | Padding interno de botones o inputs |
 * | `lg` | 16.dp | Margen general de secciones |
 * | `xl` | 24.dp | Separación entre bloques grandes |
 * | `xxl` | 32.dp | Padding externo global (pantallas) |
 *
 * ----------------------------------------------------------------------------
 * 🔹 Cómo extender:
 * ----------------------------------------------------------------------------
 * Puedes añadir tamaños personalizados, por ejemplo:
 * `val huge = 48.dp` o `val tiny = 2.dp`, según las guías de tu diseño.
 * ----------------------------------------------------------------------------
 */

@Immutable
data class Spacing(
    val xs: androidx.compose.ui.unit.Dp = 4.dp,
    val sm: androidx.compose.ui.unit.Dp = 8.dp,
    val md: androidx.compose.ui.unit.Dp = 12.dp,
    val lg: androidx.compose.ui.unit.Dp = 16.dp,
    val xl: androidx.compose.ui.unit.Dp = 24.dp,
    val xxl: androidx.compose.ui.unit.Dp = 32.dp
)

// 📦 CompositionLocal: sistema que permite acceder al espaciado en cualquier Composable
val LocalSpacing = staticCompositionLocalOf { Spacing() }

//
// 💡 Cómo usar en la app:
// -----------------------------------------------------------------------------
// KivoFitTheme.spacing.lg → padding(KivoFitTheme.spacing.lg)
//
object KivoFitTheme {
    val spacing: Spacing
        @Composable get() = LocalSpacing.current
}
