package com.KivoFit.ui.screens.auth.register

import android.util.Patterns
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

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onEmailChange(v: String) {
        _state.value = _state.value.copy(
            email = v,
            emailError = validateEmail(v),
            submitError = null
        )
    }

    fun onPasswordChange(v: String) {
        _state.value = _state.value.copy(
            password = v,
            passwordError = validatePassword(v),
            repeatError = validateRepeat(v, _state.value.repeat),
            submitError = null
        )
    }

    fun onRepeatChange(v: String) {
        _state.value = _state.value.copy(
            repeat = v,
            repeatError = validateRepeat(_state.value.password, v),
            submitError = null
        )
    }

    fun onRegisterClick() = viewModelScope.launch {
        val s = _state.value

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
