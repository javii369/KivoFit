package com.KivoFit.navigation

/**
 * ----------------------------------------------------------------------------
 * UiEvent.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Esta sealed class define los **eventos unidireccionales de UI** que un `ViewModel`
 * puede emitir hacia la capa de presentación (Compose).
 *
 * 🔹 Propósito:
 * Garantizar una comunicación **segura, reactiva y desacoplada**
 * entre ViewModel y la interfaz de usuario, siguiendo el patrón **MVI / UDF**.
 *
 * 🔹 Tipos de eventos definidos:
 * - `Navigate` → Navegar a una ruta específica.
 * - `NavigateBack` → Retroceder en el back stack.
 * - `ShowSnackbar` → Mostrar mensaje temporal.
 *
 * 🔹 Ejemplo:
 * ```kotlin
 * _events.send(UiEvent.Navigate(Route.Home.route))
 * _events.send(UiEvent.ShowSnackbar("Inicio de sesión fallido"))
 * ```
 *
 * 🔹 Flujo:
 * ViewModel → UiEvent → HandleNavigationEvents → UI/Navigation
 * ----------------------------------------------------------------------------
 */
sealed class UiEvent {

    /** 🔹 Navegación a una nueva ruta */
    data class Navigate(
        val route: String,
        val popUpTo: String? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = true
    ) : UiEvent()

    /** 🔹 Navegar hacia atrás */
    data object NavigateBack : UiEvent()

    /** 🔹 Mostrar mensaje en pantalla */
    data class ShowSnackbar(val message: String) : UiEvent()
}
