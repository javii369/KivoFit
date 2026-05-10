package com.KivoFit.ui.screens.auth.login

import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.usecase.auth.LoginUseCase
import com.KivoFit.navigation.Route
import com.KivoFit.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * ----------------------------------------------------------------------------
 * LoginViewModel.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este ViewModel gestiona toda la **lógica de negocio y validación**
 * del flujo de autenticación (login).
 *
 * Es el intermediario entre la UI (`LoginScreen`) y el dominio (`LoginUseCase`):
 *   - La UI no conoce el caso de uso ni los repositorios.
 *   - El caso de uso no conoce la UI.
 *   - Este ViewModel une ambos mundos.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * - Capa: **Presentation / ViewModel**
 * - Patrón: **MVVM (Model-View-ViewModel)**
 * - Inyección de dependencias: **Hilt**
 * - Comunicación UI ↔ ViewModel:
 *     - Estado: `StateFlow<LoginUiState>`
 *     - Eventos de navegación: `Channel<UiEvent>`
 *
 * ----------------------------------------------------------------------------
 * 🔹 Responsabilidades principales:
 * ----------------------------------------------------------------------------
 * ✅ Validar el email y la contraseña del usuario.
 * ✅ Ejecutar el caso de uso `LoginUseCase`.
 * ✅ Actualizar el estado de la UI (loading, errores, etc.).
 * ✅ Emitir eventos de navegación (`UiEvent.Navigate(...)`).
 * ✅ Mantener un código desacoplado y testable.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ciclo de interacción típico:
 * ----------------------------------------------------------------------------
 * 1️⃣ Usuario escribe → `onEmailChange` / `onPasswordChange`
 * 2️⃣ ViewModel actualiza `state` con errores o valores nuevos
 * 3️⃣ Usuario pulsa “Entrar” → `onLoginClick`
 * 4️⃣ Se validan los campos y se ejecuta `LoginUseCase`
 * 5️⃣ Si el login es correcto → `UiEvent.Navigate(Route.Inicio)`
 * 6️⃣ Si falla → Se muestra el error (`submitError`)
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo visual de flujo:
 * ----------------------------------------------------------------------------
 * LoginScreen ➜ LoginViewModel ➜ LoginUseCase ➜ FakeAuthRepository
 *          ⬑────────── state / UiEvent ───────────⬏
 * ----------------------------------------------------------------------------
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val savedStateHandle: SavedStateHandle // Permite guardar/restaurar estado tras rotación
) : ViewModel() {

    // 🔸 STATE: Estado observable que representa los datos del formulario
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    // 🔸 EVENTS: Canal de comunicación con la UI (navegación, mensajes, etc.)
    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * 📨 Se ejecuta cada vez que el usuario cambia el email.
     * Actualiza el estado y valida el formato.
     */
    fun onEmailChange(v: String) {
        _state.value = _state.value.copy(
            email = v,
            emailError = validateEmail(v),
            submitError = null // Reset del error global al volver a escribir
        )
    }

    /**
     * 🔒 Se ejecuta cada vez que el usuario cambia la contraseña.
     * Actualiza el estado y valida longitud mínima.
     */
    fun onPasswordChange(v: String) {
        _state.value = _state.value.copy(
            password = v,
            passwordError = validatePassword(v),
            submitError = null
        )
    }

    /**
     * 🚀 Lógica principal de login.
     * Valida campos, muestra loading, ejecuta el caso de uso
     * y emite eventos según el resultado.
     */
    fun onLoginClick() = viewModelScope.launch {
        val s = _state.value

        // 1️⃣ Validación previa
        val eErr = validateEmail(s.email)
        val pErr = validatePassword(s.password)
        if (eErr != null || pErr != null) {
            _state.value = s.copy(emailError = eErr, passwordError = pErr)
            return@launch
        }

        // 2️⃣ Mostrar indicador de carga
        _state.value = s.copy(isLoading = true, submitError = null)

        // 3️⃣ Ejecutar el caso de uso
        val result = loginUseCase(s.email, s.password)

        // 4️⃣ Ocultar loading
        _state.value = _state.value.copy(isLoading = false)

        // 5️⃣ Resultado del login
        result.onSuccess {
            _events.send(
                UiEvent.Navigate(
                    route = Route.Inicio.route,
                    popUpTo = Route.Login.route,
                    inclusive = true
                )
            )
        }.onFailure { ex ->
            _state.value = _state.value.copy(
                submitError = ex.message ?: "Error desconocido"
            )
        }
    }

    /**
     * 🧾 Navega hacia la pantalla de registro.
     */
    fun onRegisterClick() = viewModelScope.launch {
        _events.send(UiEvent.Navigate(Route.Register.route))
    }

    /**
     * 🔁 Navega hacia la pantalla de recuperación de contraseña.
     */
    fun onRecoverClick() = viewModelScope.launch {
        _events.send(UiEvent.Navigate(Route.RecoverPassword.route))
    }

    /**
     * ✉️ Valida el formato del email usando expresión regular de Android.
     */
    private fun validateEmail(e: String): String? =
        when {
            e.isBlank() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(e).matches() -> "Formato de email no válido"
            else -> null
        }

    /**
     * 🔑 Valida la longitud mínima de la contraseña.
     */
    private fun validatePassword(p: String): String? =
        if (p.length < 4) "Mínimo 4 caracteres" else null
}
