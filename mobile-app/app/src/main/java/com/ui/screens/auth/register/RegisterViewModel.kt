package com.KivoFit.ui.screens.auth.register

import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.usecase.auth.RegisterUseCase
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
 * RegisterViewModel.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general
 * ViewModel responsable de la **lógica de registro**:
 * - Gestiona el estado inmutable de la UI (`RegisterUiState`).
 * - Valida campos (email, password y repetición).
 * - Ejecuta el caso de uso de registro (`RegisterUseCase`).
 * - Emite **eventos de UI** (navegación y snackbars) mediante `UiEvent`.
 *
 * 🔹 Arquitectura y responsabilidades
 * - Capa: **Presentation / ViewModel** (MVVM).
 * - La UI (Compose) no tiene reglas de negocio: solo pinta `state` y llama callbacks.
 * - Comunicación unidireccional:
 *     UI ➜ onXxxChange/onRegisterClick ➜ ViewModel ➜ (update state / UiEvent) ➜ UI
 *
 * 🔹 Estado y eventos
 * - `StateFlow<RegisterUiState>`: único origen de verdad para la pantalla.
 * - `Channel<UiEvent>`: eventos efímeros (navegar, mostrar snackbar, back).
 *
 * 🔹 Decisiones de diseño
 * - Validación **inmediata** en cambios de campos (UX responsiva).
 * - En el submit, **revalida** todo (fuente de la verdad coherente).
 * - En éxito: snackbar de confirmación + `NavigateBack` (vuelta al Login).
 *
 * 🔹 SavedStateHandle (opcional)
 * - Útil si quieres persistir campos al recrear proceso/rotaciones.
 * - Si no lo usas, **elimínalo** para mantener el código limpio.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Extensiones recomendadas (futuro)
 * ----------------------------------------------------------------------------
 * - Política de contraseñas configurable (mínimo, mayúsculas, símbolos, etc.).
 * - Debounce en validaciones para inputs masivos.
 * - Internacionalización de mensajes de error (strings resources).
 * - Tests unitarios de validación y de flujo de eventos (TDD-friendly).
 * ----------------------------------------------------------------------------
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val savedStateHandle: SavedStateHandle // ❗️Opcional: elimínalo si no lo usas
) : ViewModel() {

    // Estado inmutable que consume la UI
    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    // Canal de eventos de UI (navegación, snackbars…)
    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * 📨 Email cambia: actualiza estado y (re)valida email.
     */
    fun onEmailChange(v: String) {
        _state.value = _state.value.copy(
            email = v,
            emailError = validateEmail(v),
            submitError = null // limpiar error global al teclear
        )
    }

    /**
     * 🔑 Password cambia: actualiza estado, valida password y
     * revalida la coincidencia con "repeat".
     */
    fun onPasswordChange(v: String) {
        _state.value = _state.value.copy(
            password = v,
            passwordError = validatePassword(v),
            repeatError = validateRepeat(v, _state.value.repeat),
            submitError = null
        )
    }

    /**
     * 🔁 Repetición cambia: revalida match con la password.
     */
    fun onRepeatChange(v: String) {
        _state.value = _state.value.copy(
            repeat = v,
            repeatError = validateRepeat(_state.value.password, v),
            submitError = null
        )
    }

    /**
     * 🚀 Intento de registro:
     * 1) Revalida todos los campos.
     * 2) Activa loading.
     * 3) Ejecuta caso de uso.
     * 4) Emite eventos según resultado.
     */
    fun onRegisterClick() = viewModelScope.launch {
        val s = _state.value

        // Revalidación de seguridad antes de enviar
        val eErr = validateEmail(s.email)
        val pErr = validatePassword(s.password)
        val rErr = validateRepeat(s.password, s.repeat)
        if (eErr != null || pErr != null || rErr != null) {
            _state.value = s.copy(emailError = eErr, passwordError = pErr, repeatError = rErr)
            return@launch
        }

        _state.value = s.copy(isLoading = true, submitError = null)
        val result = registerUseCase(s.email, s.password)
        _state.value = _state.value.copy(isLoading = false)

        result.onSuccess {
            // UX: confirmamos al usuario y volvemos a Login
            _events.send(UiEvent.ShowSnackbar("Cuenta creada. Inicia sesión"))
            _events.send(UiEvent.NavigateBack)
        }.onFailure { ex ->
            _state.value = _state.value.copy(
                submitError = ex.message ?: "No se pudo crear la cuenta"
            )
        }
    }

    /**
     * 🔙 Acción de volver (normalmente a Login).
     */
    fun onBackClick() = viewModelScope.launch {
        _events.send(UiEvent.NavigateBack)
    }

    // ------------------------------
    // 🔍 Validaciones (UI concerns)
    // ------------------------------

    private fun validateEmail(e: String): String? =
        when {
            e.isBlank() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(e).matches() -> "Formato de email no válido"
            else -> null
        }

    private fun validatePassword(p: String): String? =
        when {
            p.length < 6 -> "Mínimo 6 caracteres"
            else -> null
        }

    private fun validateRepeat(p: String, r: String): String? =
        when {
            r.isBlank() -> "Repite la contraseña"
            p != r -> "Las contraseñas no coinciden"
            else -> null
        }
}
