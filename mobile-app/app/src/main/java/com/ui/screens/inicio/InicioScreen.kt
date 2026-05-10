package com.KivoFit.ui.screens.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.KivoFit.R
import com.KivoFit.ui.theme.KivoFitTheme

@Composable
fun InicioScreen(
    state: InicioUiState,
    onRequestPlan: () -> Unit,
    onReserveClass: () -> Unit,
    onViewRoutine: () -> Unit
) {
    val s = KivoFitTheme.spacing

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_kivofit),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color(0xFF2D3D34)),
            alpha = 0.85f,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(s.lg)
    ) {
        Column {
            Text(
                "¡Hola, ${state.userName}!",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(s.xs))
            Text(
                "Listo para entrenar hoy",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(s.xl))

        Column(verticalArrangement = Arrangement.spacedBy(s.md)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(s.md)
            ) {
                StatCard(
                    icon = Icons.Filled.FitnessCenter,
                    value = state.trainings.toString(),
                    label = "Entrenamientos",
                    circleColor = Color(0xFF2B7FFF),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Filled.Whatshot,
                    value = formatCalories(state.calories),
                    label = "Calorías",
                    circleColor = Color(0xFFFF6900),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(s.md)
            ) {
                StatCard(
                    icon = Icons.Filled.TrendingUp,
                    value = state.streak.toString(),
                    label = "Racha",
                    circleColor = Color(0xFF00C950),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    icon = Icons.Filled.CheckCircle,
                    value = "${state.goalsCompleted}/${state.goalsTotal}",
                    label = "Objetivos",
                    circleColor = Color(0xFFAD46FF),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(s.xl))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF6933F7).copy(alpha = 0.65f)
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    "Nuevo",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(s.sm)
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = s.sm, vertical = s.xs)
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(s.lg),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFF06DF72), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FitnessCenter,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Spacer(modifier = Modifier.width(s.lg))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Plan Personalizado",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                        Spacer(modifier = Modifier.height(s.sm))
                        Text(
                            "Completa el formulario para que tu entrenador pueda diseñar tu rutina y dieta a medida",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiary,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(s.sm))
                        Text(
                            "Solicitar ahora >",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.clickable { onRequestPlan() }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(s.xl))

        Text(
            "Acciones Rápidas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(s.sm))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(s.md)
        ) {
            QuickAction(
                icon = Icons.Filled.CalendarToday,
                label = "Reservar Clase",
                subtitle = state.reserveInfo,
                onClick = onReserveClass,
                modifier = Modifier.fillMaxWidth(),
                circleColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
            QuickAction(
                icon = Icons.Filled.List,
                label = "Mi Rutina",
                subtitle = state.routineInfo,
                onClick = onViewRoutine,
                modifier = Modifier.fillMaxWidth(),
                circleColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
    }
}

@Composable
private fun StatCard(
    icon: ImageVector,
    value: String,
    label: String,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
) {
    val s = KivoFitTheme.spacing
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(s.md)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(circleColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(Modifier.height(s.sm))
            Text(
                value,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
private fun QuickAction(
    icon: ImageVector,
    label: String,
    subtitle: String? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    val s = KivoFitTheme.spacing
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(s.md)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(circleColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = contentColor
                )
            }
            Spacer(Modifier.width(s.md))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (subtitle != null) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

private fun formatCalories(value: Int): String {
    return if (value < 1000) "${value}" else "${value / 1000}.${(value % 1000) / 100}K"
}

@Composable
@Preview(showBackground = true)
fun InicioScreenPreview() {
    KivoFitTheme {
        InicioScreen(
            state = InicioUiState(
                userName = "Juan",
                trainings = 24,
                calories = 8500,
                streak = 12,
                goalsCompleted = 3,
                goalsTotal = 5,
                reserveInfo = "12 clases disponibles hoy",
                routineInfo = "Día 3 de tu plan"
            ),
            onRequestPlan = {},
            onReserveClass = {},
            onViewRoutine = {}
        )
    }
}
