package com.KivoFit.ui.screens.auth.register

/**
 * ----------------------------------------------------------------------------
 * RegisterUiState.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Representa el **estado inmutable** de la pantalla de registro de usuario.
 *
 * Este `data class` contiene todos los datos que la UI necesita para renderizarse:
 * - Los valores de los campos (`email`, `password`, `repeat`).
 * - Los posibles errores de validación.
 * - El estado de carga (`isLoading`).
 * - Los errores globales del formulario (`submitError`).
 *
 * En Compose, **la UI no almacena su propio estado**, sino que reacciona
 * a los cambios emitidos desde el `ViewModel`.
 * Por tanto, este objeto actúa como la **fuente única de verdad** (Single Source of Truth)
 * para todo lo que se muestra en la pantalla.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * ----------------------------------------------------------------------------
 * - Capa: **Presentation / UI State**
 * - Patrón: **MVVM (Model-View-ViewModel)**
 * - Gestionado por: `RegisterViewModel`
 * - Usado por: `RegisterScreen`
 *
 * El `RegisterViewModel` emite instancias de esta clase mediante un `StateFlow`,
 * y cada vez que cambia algún campo, Compose se recompone automáticamente.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Campos principales:
 * ----------------------------------------------------------------------------
 * @param email Texto actual del campo de email.
 * @param password Texto actual del campo de contraseña.
 * @param repeat Texto actual del campo "Repite la contraseña".
 * @param isLoading Indica si se está procesando la operación de registro.
 * @param emailError Mensaje de error del campo email (null si es válido).
 * @param passwordError Mensaje de error del campo contraseña (null si es válido).
 * @param repeatError Mensaje de error del campo repetición (null si es válido).
 * @param submitError Error general del formulario (por ejemplo, “email ya registrado”).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Propiedad derivada:
 * ----------------------------------------------------------------------------
 * `isValid`: calcula si el formulario puede enviarse.
 *  - Todos los campos deben estar rellenos.
 *  - No debe haber errores de validación.
 *  - Se usa en la UI para habilitar/deshabilitar el botón principal.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * val state = RegisterUiState(
 *     email = "test@correo.com",
 *     password = "1234",
 *     repeat = "1234"
 * )
 *
 * if (state.isValid) {
 *     println("Formulario válido, se puede registrar.")
 * }
 * ```
 * ----------------------------------------------------------------------------
 * 🔹 Buenas prácticas reflejadas:
 * ----------------------------------------------------------------------------
 * ✅ **Inmutabilidad:** cada cambio genera una nueva instancia.
 * ✅ **Predecible:** siempre se sabe en qué estado está la UI.
 * ✅ **Reutilizable:** el mismo modelo puede usarse en tests o previews.
 * ✅ **Declarativo:** la UI se limita a "mostrar lo que dice el estado".
 * ✅ **Mantenible:** se agrupan todos los datos relevantes en un solo lugar.
 *
 * ----------------------------------------------------------------------------
 */
data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val repeat: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatError: String? = null,
    val submitError: String? = null
) {
    /**
     * ✅ Propiedad derivada que indica si el formulario es válido.
     *
     * El formulario se considera válido si:
     * - Ninguno de los campos está vacío.
     * - No existen errores de validación.
     */
    val isValid: Boolean
        get() = email.isNotBlank() &&
                password.isNotBlank() &&
                repeat.isNotBlank() &&
                emailError == null &&
                passwordError == null &&
                repeatError == null
}
