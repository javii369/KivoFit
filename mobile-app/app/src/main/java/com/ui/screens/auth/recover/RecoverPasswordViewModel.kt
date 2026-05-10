package com.KivoFit.ui.screens.auth.recover

import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.usecase.auth.RecoverPasswordUseCase
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
class RecoverPasswordViewModel @Inject constructor(
    private val recoverUseCase: RecoverPasswordUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RecoverPasswordUiState())
    val state: StateFlow<RecoverPasswordUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onEmailChange(v: String) {
        _state.value = _state.value.copy(
            email = v,
            emailError = validateEmail(v),
            submitError = null
        )
    }

    fun onRecoverClick() = viewModelScope.launch {
        val s: RecoverPasswordUiState = _state.value
        val eErr = validateEmail(s.email)
        if (eErr != null) {
            _state.value = s.copy(emailError = eErr)
            return@launch
        }

        _state.value = s.copy(isLoading = true, submitError = null)
        val result = recoverUseCase(s.email)
        _state.value = _state.value.copy(isLoading = false)

        result.onSuccess {
            // Aviso y volver atrás al Login
            _events.send(UiEvent.ShowSnackbar("Te hemos enviado un email de recuperación"))
            _events.send(UiEvent.NavigateBack)
        }.onFailure { ex ->
            _state.value = _state.value.copy(
                submitError = ex.message ?: "No se pudo enviar el email"
            )
        }
    }

    fun onBackClick() = viewModelScope.launch { _events.send(UiEvent.NavigateBack) }

    private fun validateEmail(e: String): String? =
        when {
            e.isBlank() -> "El email es obligatorio"
            !Patterns.EMAIL_ADDRESS.matcher(e).matches() -> "Formato de email no válido"
            else -> null
        }
}