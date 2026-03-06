package com.KivoFit.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * ----------------------------------------------------------------------------
 * Color.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Define la **paleta de colores oficial** de la aplicación, tanto en modo claro
 * como oscuro, siguiendo el estándar de **Material Design 3 (Material You)**.
 *
 * Este archivo contiene:
 *  - Colores de marca (tokens base)
 *  - Esquemas de color (`LightColors` / `DarkColors`) aplicados por el tema global.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la capa **UI / Theme**
 * - Se usa dentro de `KivoFitTheme` (Theme.kt)
 * - Acceso desde cualquier parte de la UI:
 *   👉 `MaterialTheme.colorScheme.primary`
 *
 * ----------------------------------------------------------------------------
 * 🔹 Buenas prácticas:
 * ----------------------------------------------------------------------------
 * ✅ No uses directamente valores hexadecimales en los composables.
 * ✅ Usa `MaterialTheme.colorScheme` para mantener consistencia visual.
 * ✅ Los “tokens” (`BrandPrimary`, `BrandBackgroundLight`, etc.)
 *    sirven como alias semánticos reutilizables.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * Button(
 *     colors = ButtonDefaults.buttonColors(
 *         containerColor = MaterialTheme.colorScheme.primary
 *     )
 * ) {
 *     Text("Iniciar sesión", color = MaterialTheme.colorScheme.onPrimary)
 * }
 * ```
 * ----------------------------------------------------------------------------
 * 🔹 Cómo extender:
 * ----------------------------------------------------------------------------
 * - Añade nuevos tokens (por ejemplo, `BrandWarning`, `BrandSuccess`)
 * - O define nuevos roles semánticos (por ejemplo, `colorScheme.errorContainer`)
 * ----------------------------------------------------------------------------
 */

//
// 🎨 PALETA BASE (Tokens de marca)
// -----------------------------------------------------------------------------
// Estos colores se definen una sola vez y se reutilizan tanto en modo claro
// como oscuro. Representan la identidad visual del producto.
//

val BrandPrimary = Color(0xFF4F46E5)   // Azul índigo (color principal de marca)
val BrandOnPrimary = Color(0xFFFFFFFF) // Texto sobre color principal

val BrandSecondary = Color(0xFF14B8A6) // Verde azulado para botones o énfasis
val BrandOnSecondary = Color(0xFF0B0F0E)

val BrandTertiary = Color(0xFFFB7185)  // Rosa suave (elementos de acción o alerta)
val BrandOnTertiary = Color(0xFF140607)

// 🎨 Colores de fondo y superficie
val BrandBackgroundLight = Color(0xFFF7F7FB) // Fondo de pantalla claro
val BrandSurfaceLight = Color(0xFFFFFFFF)    // Tarjetas, paneles, etc.
val BrandOutlineLight = Color(0xFFE3E5EA)    // Bordes y divisores sutiles

val BrandBackgroundDark = Color(0xFF0F1115)  // Fondo oscuro
val BrandSurfaceDark = Color(0xFF141820)     // Superficies oscuras elevadas
val BrandOutlineDark = Color(0xFF2A2F3A)


//
// ☀️ ESQUEMA CLARO (Light Mode)
// -----------------------------------------------------------------------------
// Este esquema se aplica automáticamente cuando el sistema está en modo claro.
//
val LightColors = lightColorScheme(
    primary = BrandPrimary,
    onPrimary = BrandOnPrimary,

    secondary = BrandSecondary,
    onSecondary = BrandOnSecondary,

    tertiary = BrandTertiary,
    onTertiary = BrandOnTertiary,

    background = BrandBackgroundLight,
    onBackground = Color(0xFF0B0F0E),

    surface = BrandSurfaceLight,
    onSurface = Color(0xFF0B0F0E),

    outline = BrandOutlineLight
)


//
// 🌙 ESQUEMA OSCURO (Dark Mode)
// -----------------------------------------------------------------------------
// Este esquema se aplica automáticamente cuando el sistema está en modo oscuro.
//
val DarkColors = darkColorScheme(
    primary = BrandPrimary,
    onPrimary = BrandOnPrimary,

    secondary = BrandSecondary,
    onSecondary = BrandOnSecondary,

    tertiary = BrandTertiary,
    onTertiary = BrandOnTertiary,

    background = BrandBackgroundDark,
    onBackground = Color(0xFFE7E9EE),

    surface = BrandSurfaceDark,
    onSurface = Color(0xFFE7E9EE),

    outline = BrandOutlineDark
)
