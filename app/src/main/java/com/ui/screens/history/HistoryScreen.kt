package com.KivoFit.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme

@Composable
fun HistoryScreen(
    state: HistoryUiState
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
            text = state.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(s.lg))

        ProgressSection(label = "Este año", stats = state.yearStats)
        Spacer(Modifier.height(s.md))
        ProgressSection(label = "Este mes", stats = state.monthStats)
        Spacer(Modifier.height(s.md))
        ProgressSection(label = "Hoy", stats = state.dayStats)

        Spacer(Modifier.height(s.xl))

        Text(
            text = "Actividad reciente",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(s.md))

        if (state.activities.isEmpty()) {
            EmptyActivitiesPlaceholder()
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(s.md)) {
                state.activities.forEach { ActivityRow(it) }
            }
        }
    }
}

@Composable
private fun ProgressSection(label: String, stats: ProgressStats) {
    val s = KivoFitTheme.spacing
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(s.md)) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(s.sm))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(s.sm)
            ) {
                StatTile(
                    icon = Icons.Filled.FitnessCenter,
                    value = stats.workouts.toString(),
                    label = "Entrenos",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    icon = Icons.Filled.SelfImprovement,
                    value = stats.classes.toString(),
                    label = "Clases",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    icon = Icons.Filled.LocalFireDepartment,
                    value = formatCalories(stats.calories),
                    label = "Calorías",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f)
                )
                StatTile(
                    icon = Icons.Filled.AccessTime,
                    value = formatMinutes(stats.minutes),
                    label = "Tiempo",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun StatTile(
    icon: ImageVector,
    value: String,
    label: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val s = KivoFitTheme.spacing
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.medium
            )
            .padding(vertical = s.sm, horizontal = s.xs),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(tint, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(Modifier.height(s.xs))
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun EmptyActivitiesPlaceholder() {
    val s = KivoFitTheme.spacing
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(s.lg),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.History,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(Modifier.width(s.md))
            Text(
                text = "Aún no hay actividad. Tus entrenamientos y clases aparecerán aquí.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun ActivityRow(item: HistoryActivity) {
    val s = KivoFitTheme.spacing
    val (icon, tint) = when (item.kind) {
        HistoryActivityKind.Workout ->
            Icons.Filled.FitnessCenter to MaterialTheme.colorScheme.primary
        HistoryActivityKind.Class ->
            Icons.Filled.CalendarToday to MaterialTheme.colorScheme.secondary
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(s.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(tint, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(Modifier.width(s.md))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${item.dateLabel} · ${item.timeLabel}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${item.durationMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                if (item.calories != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "${formatCalories(item.calories)} cal",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

private fun formatCalories(value: Int): String =
    if (value < 1000) "$value" else "${value / 1000}.${(value % 1000) / 100}K"

private fun formatMinutes(value: Int): String {
    val hours = value / 60
    val rem = value % 60
    return when {
        hours == 0 -> "${rem}m"
        rem == 0 -> "${hours}h"
        else -> "${hours}h${rem}"
    }
}

@Composable
@Preview(showBackground = true)
private fun HistoryScreenPreview() {
    KivoFitTheme {
        HistoryScreen(
            state = HistoryUiState(
                yearStats = ProgressStats(workouts = 124, classes = 36, calories = 85000, minutes = 4800),
                monthStats = ProgressStats(workouts = 14, classes = 4, calories = 9200, minutes = 540),
                dayStats = ProgressStats(workouts = 1, classes = 1, calories = 620, minutes = 75),
                activities = listOf(
                    HistoryActivity("1", HistoryActivityKind.Class, "Funcional", "Hoy", "18:00", 60, 480),
                    HistoryActivity("2", HistoryActivityKind.Workout, "Pecho y tríceps", "Ayer", "19:30", 55, 410),
                    HistoryActivity("3", HistoryActivityKind.Class, "Yoga", "Mar 28 abr", "10:00", 45, 220)
                )
            )
        )
    }
}
