package com.KivoFit.ui.screens.notifications

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import com.KivoFit.navigation.UiEvent

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
        NotificationsUiState(
            notices = listOf(
                GymNotice(
                    id = "1",
                    kind = NoticeKind.Closure,
                    title = "Cerrado por festivo",
                    message = "El gimnasio permanecerá cerrado el 1 de mayo por el Día del Trabajo.",
                    dateLabel = "1 may"
                ),
                GymNotice(
                    id = "2",
                    kind = NoticeKind.ReducedHours,
                    title = "Horario reducido",
                    message = "Sábado 4 de mayo abriremos de 9:00 a 14:00 por mantenimiento de la sala.",
                    dateLabel = "4 may"
                ),
                GymNotice(
                    id = "3",
                    kind = NoticeKind.Event,
                    title = "Master class de spinning",
                    message = "Únete a la master class del jueves 9 de mayo a las 19:00. Plazas limitadas.",
                    dateLabel = "9 may"
                ),
                GymNotice(
                    id = "4",
                    kind = NoticeKind.Maintenance,
                    title = "Mantenimiento de cintas",
                    message = "Las cintas 3 y 4 estarán fuera de servicio hasta el viernes.",
                    dateLabel = "Esta semana"
                ),
                GymNotice(
                    id = "5",
                    kind = NoticeKind.Info,
                    title = "Nuevo monitor",
                    message = "Damos la bienvenida a Marta, nueva monitora de funcional los lunes y miércoles.",
                    dateLabel = "Hoy"
                )
            )
        )
    )
    val state: StateFlow<NotificationsUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()
}
