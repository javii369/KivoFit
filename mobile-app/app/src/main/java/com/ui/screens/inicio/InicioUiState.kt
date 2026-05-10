package com.KivoFit.ui.screens.inicio

/**
 * ----------------------------------------------------------------------------
 * InicioUiState.kt
 * ----------------------------------------------------------------------------
 *
 * Representa el estado inmutable de la pantalla **Inicio** (la auténtica
 * "pantalla principal" que ve el usuario tras iniciar sesión). A diferencia de
 * `HomeUiState` --que era un simple placeholder con productos-- aquí guardamos
 * los datos que aparecen en el diseño real del cliente (saludo, estadísticas,
 * objetivos y detalles de las acciones rápidas).
 *
 * Esta clase sigue el mismo patrón usado en otras pantallas: el ViewModel
 * emite instancias nuevas cada vez que cambia cualquier valor, y la UI se
 * recompone automáticamente.
 *
 * @param userName Nombre del usuario para el saludo ("¡Hola, Juan!").
 * @param trainings Total de entrenamientos completados.
 * @param calories Calorías quemadas (número entero; la UI formatea 8500 → "8.5K").
 * @param streak Días de racha actual.
 * @param goalsCompleted Objetivos alcanzados.
 * @param goalsTotal Objetivos totales del periodo (3/5, por ejemplo).
 * @param reserveInfo Texto descriptivo para la acción "Reservar Clase".
 * @param routineInfo Texto descriptivo para la acción "Mi Rutina".
 */
data class InicioUiState(
    val userName: String = "Juan",
    val trainings: Int = 0,
    val calories: Int = 0,
    val streak: Int = 0,
    val goalsCompleted: Int = 0,
    val goalsTotal: Int = 0,
    // subtítulos para las acciones rápidas
    val reserveInfo: String = "",
    val routineInfo: String = ""
)
