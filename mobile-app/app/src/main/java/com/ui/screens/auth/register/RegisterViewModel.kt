package com.KivoFit.ui.screens.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.model.RegisterParams
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

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onDniChange(v: String) {
        _state.value = _state.value.copy(
            dni = v.uppercase(),
            dniError = validateDni(v),
            submitError = null
        )
    }

    fun onNombreChange(v: String) {
        _state.value = _state.value.copy(
            nombre = v,
            nombreError = validateNombre(v),
            submitError = null
        )
    }

    fun onApellidoChange(v: String) {
        _state.value = _state.value.copy(
            apellido = v,
            apellidoError = validateNombre(v, "Los apellidos"),
            submitError = null
        )
    }

    fun onEmailChange(v: String) {
        _state.value = _state.value.copy(
            email = v,
            emailError = validateEmail(v),
            submitError = null
        )
    }

    fun onPasswordChange(v: String) {
        val s = _state.value
        _state.value = s.copy(
            password = v,
            passwordError = validatePassword(v),
            repeatError = validateRepeat(v, s.repeat),
            submitError = null
        )
    }

    fun onRepeatChange(v: String) {
        val s = _state.value
        _state.value = s.copy(
            repeat = v,
            repeatError = validateRepeat(s.password, v),
            submitError = null
        )
    }

    fun onRegisterClick() = viewModelScope.launch {
        val s = _state.value

        val dErr = validateDni(s.dni)
        val nErr = validateNombre(s.nombre)
        val aErr = validateNombre(s.apellido, "Los apellidos")
        val eErr = validateEmail(s.email)
        val pErr = validatePassword(s.password)
        val rErr = validateRepeat(s.password, s.repeat)
        if (listOf(dErr, nErr, aErr, eErr, pErr, rErr).any { it != null }) {
            _state.value = s.copy(
                dniError = dErr,
                nombreError = nErr,
                apellidoError = aErr,
                emailError = eErr,
                passwordError = pErr,
                repeatError = rErr
            )
            return@launch
        }

        _state.value = s.copy(isLoading = true, submitError = null)
        val params = RegisterParams(
            dni = s.dni.trim(),
            nombre = s.nombre.trim(),
            apellido = s.apellido.trim(),
            email = s.email.trim(),
            password = s.password,
            rol = "cliente"
        )
        val result = registerUseCase(params)
        _state.value = _state.value.copy(isLoading = false)

        result.onSuccess {
            _events.send(UiEvent.ShowSnackbar("Cuenta creada. Inicia sesión"))
            _events.send(UiEvent.NavigateBack)
        }.onFailure { ex ->
            _state.value = _state.value.copy(
                submitError = ex.message ?: "No se pudo crear la cuenta"
            )
        }
    }

    fun onBackClick() = viewModelScope.launch {
        _events.send(UiEvent.NavigateBack)
    }

    private fun validateEmail(e: String): String? =
        when {
            e.isBlank() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(e).matches() -> "Formato de email no válido"
            else -> null
        }

    private fun validatePassword(p: String): String? =
        when {
            p.length < 8 -> "Mínimo 8 caracteres (servidor)"
            else -> null
        }

    private fun validateRepeat(p: String, r: String): String? =
        when {
            r.isBlank() -> "Repite la contraseña"
            p != r -> "Las contraseñas no coinciden"
            else -> null
        }

    private fun validateNombre(text: String, label: String = "El nombre"): String? =
        when {
            text.isBlank() -> "$label es obligatorio"
            text.trim().length < 2 -> "$label es demasiado corto"
            else -> null
        }

    private fun validateDni(dni: String): String? {
        val t = dni.trim().uppercase()
        return when {
            t.isBlank() -> "El DNI es obligatorio"
            t.length > 9 -> "Máximo 9 caracteres"
            !t.matches(Regex("^[0-9A-Z]{1,9}$")) -> "Solo letras y números"
            else -> null
        }
    }
}
