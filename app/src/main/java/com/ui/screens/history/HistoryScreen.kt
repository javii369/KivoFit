package com.KivoFit.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Whatshot
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme

/**
 * ----------------------------------------------------------------------------
 * HistoryScreen.kt
 * ----------------------------------------------------------------------------
 *
 * Composable de UI pura para la pantalla Historial. Recibe un `HistoryUiState`
 * y muestra el contenido apropiado. Mantiene la misma estructura de HomeScreen
 * (estado + callbacks) aunque en este placeholder no hay callbacks.
 */

@Composable
fun HistoryScreen(
    state: HistoryUiState
) {
    val s = KivoFitTheme.spacing

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1635))
            .padding(horizontal = s.lg, vertical = s.xl),
        verticalArrangement = Arrangement.spacedBy(s.lg)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFFA8B3CF)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(s.md)
            ) {
                MetricCard(
                    icon = Icons.Filled.FitnessCenter,
                    value = state.trainings,
                    label = "Entrenamientos",
                    cardColor = Color(0xFF2B7FFF),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    icon = Icons.Filled.Whatshot,
                    value = state.calories,
                    label = "Calorias",
                    cardColor = Color(0xFFFF6900),
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    icon = Icons.Filled.History,
                    value = state.totalTime,
                    label = "Tiempo Total",
                    cardColor = Color(0xFF00C950),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            WeeklyProgressCard(days = state.weekDays)
        }

        item {
            Text(
                text = "Entrenamientos Recientes",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }

        items(state.recentWorkouts) { workout ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF13284D))
            ) {
                Text(
                    text = workout,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFFE6EEFF),
                    modifier = Modifier.padding(horizontal = s.lg, vertical = s.md)
                )
            }
        }
    }
}

@Composable
private fun MetricCard(
    icon: ImageVector,
    value: String,
    label: String,
    cardColor: Color,
    modifier: Modifier = Modifier
) {
    val s = KivoFitTheme.spacing

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = s.md, horizontal = s.sm),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }
}

@Composable
private fun WeeklyProgressCard(days: List<String>) {
    val s = KivoFitTheme.spacing

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF2A3F66),
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2A47))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(s.lg)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Progreso Semanal",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Filled.TrendingUp,
                    contentDescription = "Tendencia positiva",
                    tint = Color(0xFF00E56B)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                days.forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF9CB0D1)
                    )
                }
            }
        }
    }
}
