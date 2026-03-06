package com.KivoFit.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * ----------------------------------------------------------------------------
 * Shape.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Define los **shapes globales** (esquinas redondeadas) usados en toda la app.
 * Estos shapes controlan el *corner radius* de componentes como:
 * Buttons, Cards, Dialogs, TextFields, etc.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **UI / Theme**
 * - Se aplica automáticamente dentro de `KivoFitTheme`.
 * - Acceso global mediante `MaterialTheme.shapes`.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Por qué es importante:
 * ----------------------------------------------------------------------------
 * ✅ Unifica el estilo visual de los componentes.
 * ✅ Facilita la personalización del look & feel.
 * ✅ Permite adaptar el diseño a un branding concreto.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * Card(
 *     shape = MaterialTheme.shapes.medium
 * ) {
 *     Text("Tarjeta con esquinas redondeadas")
 * }
 * ```
 *
 * ----------------------------------------------------------------------------
 * 🔹 Guía de tamaños (recomendación):
 * ----------------------------------------------------------------------------
 * - **extraSmall (6.dp)** → Chips, Badges
 * - **small (10.dp)** → Botones, TextFields
 * - **medium (14.dp)** → Cards, Containers medianos
 * - **large (20.dp)** → Diálogos, Sheets, Panels
 * - **extraLarge (28.dp)** → Elementos destacados (hero containers, banners)
 *
 * ----------------------------------------------------------------------------
 * 🔹 Cómo personalizar:
 * ----------------------------------------------------------------------------
 * Puedes crear diferentes estilos según el branding:
 *  - AppShapes = formas estándar
 *  - RoundedShapes = formas más suaves
 *  - FlatShapes = sin bordes (0.dp)
 * ----------------------------------------------------------------------------
 */

// 🎨 Sistema global de shapes
// -----------------------------------------------------------------------------
// Cómo usar en la app: MaterialTheme.shapes.medium (por ejemplo, en Card/Surface)
val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)
