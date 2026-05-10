package com.KivoFit.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.domain.model.SesionBrief
import com.KivoFit.domain.repository.schedule.SesionesRepository
import com.KivoFit.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val sesionesRepository: SesionesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarUiState())
    val state: StateFlow<CalendarUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        reload()
    }

    fun reload() = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true, errorMessage = null)
        }
        sesionesRepository.listSesiones()
            .onSuccess { briefs ->
                val sorted = briefs.sortedWith(
                    compareBy({ it.fechaIso }, { it.horaInicio ?: "" })
                )
                _state.update {
                    it.copy(
                        isLoading = false,
                        classes = sorted.map { b -> b.toCalendarClass() }
                    )
                }
            }
            .onFailure { error ->
                val msg = error.message ?: "No se pudieron cargar las sesiones"
                _state.update { it.copy(isLoading = false, errorMessage = msg) }
                _events.send(UiEvent.ShowSnackbar(msg))
            }
    }

    private fun SesionBrief.toCalendarClass(): CalendarClass =
        CalendarClass(
            id = id,
            name = nombreClase,
            dateLabel = formatDateLabelEs(fechaIso),
            timeLabel = formatHorario(horaInicio),
            durationMinutes = duracionMinutos.coerceAtLeast(1)
        )

    private fun formatDateLabelEs(fechaIso: String): String {
        val normalized = fechaIso.take(10)
        return try {
            val locale = Locale("es", "ES")
            val parsed = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).parse(normalized)
            if (parsed == null) normalized
            else SimpleDateFormat("EEE d MMM", locale).format(parsed).replaceFirstChar {
                it.titlecase(locale)
            }
        } catch (_: Exception) {
            fechaIso
        }
    }

    private fun formatHorario(hora: String?): String {
        val h = hora?.trim().orEmpty()
        if (h.length >= 5) return h.take(5)
        if (h.isNotEmpty()) return h
        return "--:--"
    }
}
