package com.KivoFit.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.navigation.Route
import com.KivoFit.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ProfileUiState())
    val state: StateFlow<ProfileUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onToggleNotifications(value: Boolean) {
        _state.update { it.copy(notificationsEnabled = value) }
    }

    fun onToggleDarkMode(value: Boolean) {
        _state.update { it.copy(darkMode = value) }
    }

    fun onChangePhoto() = viewModelScope.launch {
        _events.send(UiEvent.ShowSnackbar("Cambiar foto (próximamente)"))
    }

    fun onEditProfile() = viewModelScope.launch {
        _events.send(UiEvent.ShowSnackbar("Editar perfil (próximamente)"))
    }

    fun onChangePassword() = viewModelScope.launch {
        _events.send(UiEvent.ShowSnackbar("Cambiar contraseña (próximamente)"))
    }

    fun onHelp() = viewModelScope.launch {
        _events.send(UiEvent.ShowSnackbar("Ayuda y soporte (próximamente)"))
    }

    fun onLogout() = viewModelScope.launch {
        _events.send(
            UiEvent.Navigate(
                route = Route.Login.route,
                popUpTo = Route.Inicio.route,
                inclusive = true
            )
        )
    }
}
