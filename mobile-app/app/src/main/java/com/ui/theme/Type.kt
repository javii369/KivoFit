package com.KivoFit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * ----------------------------------------------------------------------------
 * Type.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Define la **tipografía base** de la aplicación.
 *
 * Este archivo establece los tamaños, pesos y alturas de línea (lineHeight)
 * que conforman la jerarquía visual de los textos.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **UI / Theme**
 * - Integrado dentro de `MaterialTheme` en `Theme.kt`.
 * - Acceso global mediante `MaterialTheme.typography`.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué es importante:
 * ----------------------------------------------------------------------------
 * ✅ Garantiza consistencia visual entre pantallas.
 * ✅ Mejora la legibilidad y jerarquía de la información.
 * ✅ Facilita el reemplazo de la fuente sin cambiar el diseño.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * Text(
 *     text = "Bienvenido",
 *     style = MaterialTheme.typography.titleLarge
 * )
 * ```
 *
 * También puedes modificar dinámicamente:
 * ```kotlin
 * Text(
 *     text = "Hola",
 *     style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
 * )
 * ```
 *
 * ----------------------------------------------------------------------------
 * 🔹 Cómo personalizar:
 * ----------------------------------------------------------------------------
 * - Cambia `FontFamily.SansSerif` por una fuente personalizada (`@font/...`)
 * - Ajusta pesos (`FontWeight.Bold`, `Medium`, `Normal`, etc.)
 * - Modifica los tamaños (`sp`) según las guías de tu diseño.
 * ----------------------------------------------------------------------------
 */

// 🔤 Tipografía base de la app
// Puedes reemplazar esta fuente por tu propia familia en res/font/
private val AppFont = FontFamily.SansSerif

// 🎨 Sistema tipográfico global
// -----------------------------------------------------------------------------
// Cómo usar en la app:
// MaterialTheme.typography.titleLarge
// MaterialTheme.typography.bodyMedium
// -----------------------------------------------------------------------------
val AppTypography = Typography(
    // 🏷️ Título principal de pantallas o secciones grandes
    displayLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),

    // 🔹 Titulares intermedios (por ejemplo, “Inicia sesión”)
    headlineMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),

    // 🔸 Subtítulos o títulos de tarjetas
    titleLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),

    // 🧾 Texto de cuerpo (normal, párrafos largos)
    bodyLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    // ✏️ Texto secundario o explicaciones pequeñas
    bodyMedium = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // 🪪 Etiquetas, botones, pequeños labels
    labelLarge = TextStyle(
        fontFamily = AppFont,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)
