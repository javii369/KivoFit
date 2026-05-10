package com.KivoFit.ui.screens.auth.login

/**
 * ----------------------------------------------------------------------------
 * LoginUiState.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Representa el **estado inmutable** de la pantalla de login.
 *
 * Este `data class` es la fuente única de verdad (single source of truth)
 * para todos los elementos visuales de `LoginScreen`.
 *
 * En Compose, la UI **no almacena datos propios**; se limita a representar
 * este estado y reaccionar a los cambios que emita el `ViewModel`.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la capa **UI / Presentation** del patrón **MVVM**.
 * - Es gestionado por el `LoginViewModel`, que actualiza este estado
 *   mediante `MutableStateFlow` o `MutableState`.
 * - El `LoginScreen` lo recibe como `state` y se vuelve a recomponer
 *   automáticamente cada vez que cambian sus valores.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Campos:
 * ----------------------------------------------------------------------------
 * @param email Texto actual introducido en el campo de email.
 * @param password Texto actual del campo de contraseña.
 * @param isLoading Indica si se está procesando el login (muestra spinner).
 * @param emailError Mensaje de error del campo email (null si es válido).
 * @param passwordError Mensaje de error del campo password (null si es válido).
 * @param submitError Error general del formulario (por ejemplo, credenciales incorrectas).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Propiedad derivada:
 * ----------------------------------------------------------------------------
 * `isValid`: Calcula si el formulario es válido para permitir enviar.
 *  - Comprueba que no haya errores de validación.
 *  - Verifica que los campos no estén vacíos.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * val state = LoginUiState(
 *     email = "admin@ceac.com",
 *     password = "1234",
 *     isLoading = false
 * )
 *
 * if (state.isValid) {
 *     // Permitir el login
 * }
 * ```
 * ----------------------------------------------------------------------------
 */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val submitError: String? = null
) {

    /**
     * ✅ Propiedad derivada que indica si el formulario es válido.
     * Se considera válido si:
     * - No hay errores de validación (`emailError` y `passwordError` son null)
     * - Ambos campos están rellenos
     */
    val isValid: Boolean
        get() = emailError == null &&
                passwordError == null &&
                email.isNotBlank() &&
                password.isNotBlank()
}
