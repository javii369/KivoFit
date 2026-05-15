package com.KivoFit.ui.screens.plan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import com.KivoFit.ui.theme.KivoFitTheme

@Composable
fun PlanFormScreen(
    state: PlanFormUiState,
    onFullName: (String) -> Unit,
    onAge: (String) -> Unit,
    onGender: (Gender) -> Unit,
    onHeight: (String) -> Unit,
    onWeight: (String) -> Unit,
    onExperience: (ExperienceLevel) -> Unit,
    onGoal: (TrainingGoal) -> Unit,
    onDays: (Int) -> Unit,
    onIntolerances: (String) -> Unit,
    onMedical: (String) -> Unit,
    onDiet: (DietPreference) -> Unit,
    onNotes: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val s = KivoFitTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(s.lg)
    ) {
        Text(
            "Plan personalizado",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            "Cuéntanos sobre ti para diseñar tu rutina y dieta a medida.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(s.lg))

        Section("Datos personales") {
            OutlinedTextField(
                value = state.fullName,
                onValueChange = onFullName,
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(Modifier.height(s.sm))
            Row(horizontalArrangement = Arrangement.spacedBy(s.sm)) {
                OutlinedTextField(
                    value = state.age,
                    onValueChange = onAge,
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.heightCm,
                    onValueChange = onHeight,
                    label = { Text("Altura (cm)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.weightKg,
                    onValueChange = onWeight,
                    label = { Text("Peso (kg)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            Spacer(Modifier.height(s.sm))
            Text("Sexo", style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
            Spacer(Modifier.height(s.xs))
            ChipGroup(
                options = Gender.values().toList(),
                selected = state.gender,
                labelOf = { it.label },
                onSelect = onGender
            )
        }

        Spacer(Modifier.height(s.lg))

        Section("Entrenamiento") {
            Text("Nivel de experiencia",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
            Spacer(Modifier.height(s.xs))
            ChipGroup(
                options = ExperienceLevel.values().toList(),
                selected = state.experience,
                labelOf = { it.label },
                onSelect = onExperience
            )
            Spacer(Modifier.height(s.md))

            Text("Objetivo principal",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
            Spacer(Modifier.height(s.xs))
            ChipGroup(
                options = TrainingGoal.values().toList(),
                selected = state.goal,
                labelOf = { it.label },
                onSelect = onGoal
            )
            Spacer(Modifier.height(s.md))

            Text("Días por semana",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
            Spacer(Modifier.height(s.xs))
            Counter(value = state.daysPerWeek, onChange = onDays, min = 1, max = 7)
        }

        Spacer(Modifier.height(s.lg))

        Section("Dieta y salud") {
            Text("Preferencia alimentaria",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f))
            Spacer(Modifier.height(s.xs))
            ChipGroup(
                options = DietPreference.values().toList(),
                selected = state.dietPreference,
                labelOf = { it.label },
                onSelect = onDiet
            )
            Spacer(Modifier.height(s.md))
            OutlinedTextField(
                value = state.intolerances,
                onValueChange = onIntolerances,
                label = { Text("Intolerancias o alergias") },
                placeholder = { Text("p. ej. lactosa, frutos secos") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(s.sm))
            OutlinedTextField(
                value = state.medicalConditions,
                onValueChange = onMedical,
                label = { Text("Condiciones médicas / lesiones") },
                placeholder = { Text("p. ej. lumbalgia, asma") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(s.lg))

        Section("Notas adicionales") {
            OutlinedTextField(
                value = state.notes,
                onValueChange = onNotes,
                label = { Text("¿Algo más que tu entrenador deba saber?") },
                modifier = Modifier.fillMaxWidth().height(120.dp)
            )
        }

        Spacer(Modifier.height(s.xl))

        Button(
            onClick = onSubmit,
            enabled = !state.submitting,
            modifier = Modifier.fillMaxWidth().height(52.dp)
        ) {
            if (state.submitting) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Solicitar plan", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable ColumnScope.() -> Unit) {
    val s = KivoFitTheme.spacing
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(s.md)) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(s.sm))
            content()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun <T> ChipGroup(
    options: List<T>,
    selected: T?,
    labelOf: (T) -> String,
    onSelect: (T) -> Unit
) {
    val s = KivoFitTheme.spacing
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(s.xs),
        verticalArrangement = Arrangement.spacedBy(s.xs)
    ) {
        options.forEach { option ->
            val isSelected = option == selected
            val bg = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface
            val fg = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface
            Box(
                modifier = Modifier
                    .background(bg, RoundedCornerShape(50))
                    .clickable { onSelect(option) }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text(labelOf(option), style = MaterialTheme.typography.labelLarge, color = fg)
            }
        }
    }
}

@Composable
private fun Counter(value: Int, onChange: (Int) -> Unit, min: Int, max: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { if (value > min) onChange(value - 1) }) {
            Icon(Icons.Filled.Remove, contentDescription = "Restar")
        }
        Text(
            "$value",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        IconButton(onClick = { if (value < max) onChange(value + 1) }) {
            Icon(Icons.Filled.Add, contentDescription = "Sumar")
        }
    }
}
