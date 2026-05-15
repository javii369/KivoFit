package com.KivoFit.ui.screens.plan

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.KivoFit.navigation.HandleNavigationEvents

@Composable
fun PlanFormEntry(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val vm: PlanFormViewModel = hiltViewModel()
    HandleNavigationEvents(navController, snackbarHostState, vm.events)
    val state by vm.state.collectAsState()

    PlanFormScreen(
        state = state,
        onFullName = vm::onFullNameChange,
        onAge = vm::onAgeChange,
        onGender = vm::onGenderChange,
        onHeight = vm::onHeightChange,
        onWeight = vm::onWeightChange,
        onExperience = vm::onExperienceChange,
        onGoal = vm::onGoalChange,
        onDays = vm::onDaysPerWeekChange,
        onIntolerances = vm::onIntolerancesChange,
        onMedical = vm::onMedicalConditionsChange,
        onDiet = vm::onDietChange,
        onNotes = vm::onNotesChange,
        onSubmit = vm::onSubmit
    )
}
