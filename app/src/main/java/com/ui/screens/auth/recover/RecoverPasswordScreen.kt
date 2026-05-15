package com.KivoFit.ui.screens.auth.recover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.theme.KivoFitTheme

@Composable
fun RecoverPasswordScreen(
    state: RecoverPasswordUiState,
    onEmailChange: (String) -> Unit,
    onRecoverClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val s = KivoFitTheme.spacing

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = s.xl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(s.lg),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar contraseña", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = state.email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                singleLine = true,
                isError = state.emailError != null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )
            state.emailError?.let {
                Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelLarge)
            }

            state.submitError?.let {
                Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
            }

            Button(
                onClick = onRecoverClick,
                enabled = state.isValid && !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) CircularProgressIndicator(strokeWidth = 2.dp) else Text("Enviar email")
            }

            TextButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.End)
            ) { Text("Volver") }
        }
    }
}
