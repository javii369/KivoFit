package com.KivoFit.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.DoNotDisturb
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Schedule
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
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme

@Composable
fun NotificationsScreen(state: NotificationsUiState) {
    val s = KivoFitTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(s.lg)
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(s.md))

        if (state.notices.isEmpty()) {
            Text(
                "No hay avisos por ahora.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(s.md)) {
                items(state.notices, key = { it.id }) { NoticeCard(it) }
            }
        }
    }
}

@Composable
private fun NoticeCard(notice: GymNotice) {
    val s = KivoFitTheme.spacing
    val (icon, tint) = noticeIconAndTint(notice.kind)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(s.md),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier.size(40.dp).background(tint, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
            }
            Spacer(Modifier.width(s.md))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        notice.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        notice.dateLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    notice.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                )
            }
        }
    }
}

@Composable
private fun noticeIconAndTint(kind: NoticeKind): Pair<ImageVector, Color> = when (kind) {
    NoticeKind.Closure -> Icons.Filled.DoNotDisturb to MaterialTheme.colorScheme.error
    NoticeKind.ReducedHours -> Icons.Filled.Schedule to MaterialTheme.colorScheme.tertiary
    NoticeKind.Event -> Icons.Filled.Campaign to MaterialTheme.colorScheme.primary
    NoticeKind.Maintenance -> Icons.Filled.Build to MaterialTheme.colorScheme.secondary
    NoticeKind.Info -> Icons.Filled.Info to MaterialTheme.colorScheme.primary
}
