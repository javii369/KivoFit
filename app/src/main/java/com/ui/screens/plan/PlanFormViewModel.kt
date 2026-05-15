package com.KivoFit.ui.screens.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.KivoFit.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PlanFormViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(PlanFormUiState())
    val state: StateFlow<PlanFormUiState> = _state.asStateFlow()

    private val _events = Channel<UiEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onFullNameChange(v: String) = _state.update { it.copy(fullName = v) }
    fun onAgeChange(v: String) = _state.update { it.copy(age = v.filter { c -> c.isDigit() }) }
    fun onGenderChange(v: Gender) = _state.update { it.copy(gender = v) }
    fun onHeightChange(v: String) = _state.update { it.copy(heightCm = v.filter { c -> c.isDigit() || c == '.' }) }
    fun onWeightChange(v: String) = _state.update { it.copy(weightKg = v.filter { c -> c.isDigit() || c == '.' }) }
    fun onExperienceChange(v: ExperienceLevel) = _state.update { it.copy(experience = v) }
    fun onGoalChange(v: TrainingGoal) = _state.update { it.copy(goal = v) }
    fun onDaysPerWeekChange(v: Int) = _state.update { it.copy(daysPerWeek = v.coerceIn(1, 7)) }
    fun onIntolerancesChange(v: String) = _state.update { it.copy(intolerances = v) }
    fun onMedicalConditionsChange(v: String) = _state.update { it.copy(medicalConditions = v) }
    fun onDietChange(v: DietPreference) = _state.update { it.copy(dietPreference = v) }
    fun onNotesChange(v: String) = _state.update { it.copy(notes = v) }

    fun onSubmit() = viewModelScope.launch {
        if (!_state.value.isValid) {
            _events.send(UiEvent.ShowSnackbar("Completa los campos obligatorios"))
            return@launch
        }
        _state.update { it.copy(submitting = true) }
        delay(800)
        _state.update { it.copy(submitting = false) }
        _events.send(UiEvent.ShowSnackbar("Plan solicitado. Tu entrenador te contactará pronto."))
        _events.send(UiEvent.NavigateBack)
    }
}
