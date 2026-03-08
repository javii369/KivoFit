package com.KivoFit.ui.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme

@Composable
fun CalendarScreen(
    state: CalendarUiState,
    contentPadding: PaddingValues
) {
    val s = KivoFitTheme.spacing
    val days = listOf(
        DayItem("Dom", 8),
        DayItem("Lun", 9),
        DayItem("Mar", 10),
        DayItem("Mie", 11),
        DayItem("Jue", 12),
        DayItem("Vie", 13)
    )
    var selectedDay by rememberSaveable { mutableStateOf(8) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = s.lg, vertical = s.lg)
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(s.sm))

        Text(
            text = "Reserva tus clases favoritas",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(s.lg))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(s.sm)) {
            items(days) { day ->
                DayChip(
                    day = day,
                    selected = selectedDay == day.number,
                    onClick = { selectedDay = day.number }
                )
            }
        }

        Spacer(modifier = Modifier.height(s.xl))

        Text(
            text = "Clases Disponibles - $selectedDay de marzo",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(s.md))

        ClassCard()

        Spacer(modifier = Modifier.height(s.xl))
    }
}

@Composable
private fun DayChip(
    day: DayItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val selectedGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF8A2AFF), Color(0xFF5D34F8))
    )
    val chipShape = RoundedCornerShape(16.dp)

    Column(
        modifier = Modifier
            .width(82.dp)
            .height(100.dp)
            .background(
                brush = if (selected) selectedGradient else Brush.verticalGradient(
                    colors = listOf(Color(0xFF1C2C45), Color(0xFF1A2940))
                ),
                shape = chipShape
            )
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.day,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = if (selected) 0.95f else 0.65f)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = day.number.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun ClassCard() {
    val s = KivoFitTheme.spacing
    val progressGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF9C1AFF), Color(0xFF5E45FF))
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f),
                shape = MaterialTheme.shapes.large
            ),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2D44))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(s.lg)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Yoga Matutino",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Principiante",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .background(Color(0xFF00C950), shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = s.md, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Laura Ruiz",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
            )

            Spacer(modifier = Modifier.height(s.md))

            InfoRow(icon = Icons.Outlined.AccessTime, text = "09:00 - 60 min")
            Spacer(modifier = Modifier.height(s.sm))
            InfoRow(icon = Icons.Outlined.LocationOn, text = "Sala 1")
            Spacer(modifier = Modifier.height(s.sm))
            InfoRow(
                icon = Icons.Outlined.PeopleOutline,
                text = "8/15 reservados",
                textColor = Color(0xFF00E36A)
            )

            Spacer(modifier = Modifier.height(s.md))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0xFF37465E), RoundedCornerShape(50))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.53f)
                        .height(10.dp)
                        .background(progressGradient, RoundedCornerShape(50))
                )
            }

            Spacer(modifier = Modifier.height(s.lg))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(progressGradient, shape = MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Reservar Clase",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    icon: ImageVector,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor
        )
    }
}

private data class DayItem(
    val day: String,
    val number: Int
)
