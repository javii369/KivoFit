package com.KivoFit.ui.screens.plan

data class PlanFormUiState(
    val fullName: String = "",
    val age: String = "",
    val gender: Gender? = null,
    val heightCm: String = "",
    val weightKg: String = "",
    val experience: ExperienceLevel? = null,
    val goal: TrainingGoal? = null,
    val daysPerWeek: Int = 3,
    val intolerances: String = "",
    val medicalConditions: String = "",
    val dietPreference: DietPreference? = null,
    val notes: String = "",
    val submitting: Boolean = false
) {
    val isValid: Boolean
        get() = fullName.isNotBlank() &&
                age.toIntOrNull()?.let { it in 12..99 } == true &&
                weightKg.toFloatOrNull() != null &&
                heightCm.toFloatOrNull() != null &&
                gender != null &&
                goal != null &&
                experience != null &&
                dietPreference != null
}

enum class Gender(val label: String) {
    Male("Hombre"), Female("Mujer"), Other("Otro")
}

enum class ExperienceLevel(val label: String) {
    Beginner("Principiante"),
    Intermediate("Intermedio"),
    Advanced("Avanzado")
}

enum class TrainingGoal(val label: String) {
    LoseWeight("Perder peso"),
    GainMuscle("Ganar músculo"),
    Tone("Tonificar"),
    Endurance("Resistencia"),
    Health("Salud general")
}

enum class DietPreference(val label: String) {
    Omnivore("Omnívora"),
    Vegetarian("Vegetariana"),
    Vegan("Vegana"),
    Pescatarian("Pescetariana"),
    Keto("Keto")
}
