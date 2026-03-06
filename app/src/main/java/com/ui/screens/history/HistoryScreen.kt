package com.KivoFit.ui.screens.history

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(KivoFitTheme.spacing.lg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
    }
}
