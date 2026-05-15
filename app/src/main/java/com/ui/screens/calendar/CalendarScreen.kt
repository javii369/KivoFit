package com.KivoFit.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen(
    state: CalendarUiState
) {
    val s = KivoFitTheme.spacing
    val today = remember { Calendar.getInstance() }

    var displayedYear by remember { mutableIntStateOf(today.get(Calendar.YEAR)) }
    var displayedMonth by remember { mutableIntStateOf(today.get(Calendar.MONTH)) }

    var selectedYear by remember { mutableIntStateOf(today.get(Calendar.YEAR)) }
    var selectedMonth by remember { mutableIntStateOf(today.get(Calendar.MONTH)) }
    var selectedDay by remember { mutableIntStateOf(today.get(Calendar.DAY_OF_MONTH)) }

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

        MonthCalendar(
            displayedYear = displayedYear,
            displayedMonth = displayedMonth,
            today = today,
            selectedYear = selectedYear,
            selectedMonth = selectedMonth,
            selectedDay = selectedDay,
            onPrev = {
                val cal = Calendar.getInstance().apply {
                    set(displayedYear, displayedMonth, 1)
                    add(Calendar.MONTH, -1)
                }
                displayedYear = cal.get(Calendar.YEAR)
                displayedMonth = cal.get(Calendar.MONTH)
            },
            onNext = {
                val cal = Calendar.getInstance().apply {
                    set(displayedYear, displayedMonth, 1)
                    add(Calendar.MONTH, 1)
                }
                displayedYear = cal.get(Calendar.YEAR)
                displayedMonth = cal.get(Calendar.MONTH)
            },
            onDaySelected = { y, m, d ->
                selectedYear = y
                selectedMonth = m
                selectedDay = d
            }
        )

        Spacer(Modifier.height(s.xl))

        Text(
            text = "Clases disponibles",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(s.md))

        if (state.classes.isEmpty()) {
            EmptyClassesPlaceholder()
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(s.md)) {
                state.classes.forEach { item ->
                    ClassRow(item)
                }
            }
        }
    }
}

@Composable
private fun MonthCalendar(
    displayedYear: Int,
    displayedMonth: Int,
    today: Calendar,
    selectedYear: Int,
    selectedMonth: Int,
    selectedDay: Int,
    onPrev: () -> Unit,
    onNext: () -> Unit,
    onDaySelected: (Int, Int, Int) -> Unit
) {
    val s = KivoFitTheme.spacing
    val locale = Locale("es", "ES")

    val displayed = remember(displayedYear, displayedMonth) {
        Calendar.getInstance().apply { set(displayedYear, displayedMonth, 1) }
    }
    val monthLabel = remember(displayedYear, displayedMonth) {
        SimpleDateFormat("LLLL yyyy", locale)
            .format(displayed.time)
            .replaceFirstChar { it.titlecase(locale) }
    }
    val daysInMonth = displayed.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek = ((displayed.get(Calendar.DAY_OF_WEEK) + 5) % 7)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(s.md)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onPrev) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "Mes anterior",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Text(
                    text = monthLabel,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = onNext) {
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = "Mes siguiente",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(Modifier.height(s.sm))

            val weekdays = listOf("L", "M", "X", "J", "V", "S", "D")
            Row(modifier = Modifier.fillMaxWidth()) {
                weekdays.forEach { wd ->
                    Text(
                        text = wd,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(Modifier.height(s.xs))

            val totalCells = firstDayOfWeek + daysInMonth
            val rows = (totalCells + 6) / 7
            for (row in 0 until rows) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (col in 0..6) {
                        val cellIndex = row * 7 + col
                        val day = cellIndex - firstDayOfWeek + 1
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            if (day in 1..daysInMonth) {
                                val isToday = today.get(Calendar.YEAR) == displayedYear &&
                                        today.get(Calendar.MONTH) == displayedMonth &&
                                        today.get(Calendar.DAY_OF_MONTH) == day
                                val isSelected = selectedYear == displayedYear &&
                                        selectedMonth == displayedMonth &&
                                        selectedDay == day
                                DayCell(
                                    day = day,
                                    isToday = isToday,
                                    isSelected = isSelected,
                                    onClick = {
                                        onDaySelected(displayedYear, displayedMonth, day)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DayCell(
    day: Int,
    isToday: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bg = when {
        isSelected -> MaterialTheme.colorScheme.primary
        isToday -> MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        else -> Color.Transparent
    }
    val fg = if (isSelected) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .size(36.dp)
            .background(bg, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = fg,
            fontWeight = if (isToday || isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun EmptyClassesPlaceholder() {
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
                imageVector = Icons.Filled.Event,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Spacer(Modifier.width(s.md))
            Text(
                text = "Aún no hay clases disponibles. Cuando se publiquen aparecerán aquí día por día con su fecha y duración aproximada.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun ClassRow(item: CalendarClass) {
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
                .padding(s.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.FitnessCenter,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(s.xs))
                Text(
                    text = "${item.durationMinutes} min",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CalendarScreenEmptyPreview() {
    KivoFitTheme {
        CalendarScreen(state = CalendarUiState())
    }
}

@Composable
@Preview(showBackground = true)
private fun CalendarScreenWithClassesPreview() {
    KivoFitTheme {
        CalendarScreen(
            state = CalendarUiState(
                classes = listOf(
                    CalendarClass("1", "Funcional", "Lun 5 May", "18:00", 60),
                    CalendarClass("2", "Yoga", "Mar 6 May", "10:00", 45),
                    CalendarClass("3", "HIIT", "Mié 7 May", "19:30", 30)
                )
            )
        )
    }
}
