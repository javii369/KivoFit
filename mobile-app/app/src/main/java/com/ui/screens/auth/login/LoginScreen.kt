package com.KivoFit.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.KivoFit.ui.screens.auth.login.LoginUiState
import com.KivoFit.ui.theme.KivoFitTheme

/**
 * ----------------------------------------------------------------------------
 * LoginScreen.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Pantalla de inicio de sesión (UI) que forma parte del flujo de autenticación.
 * Presenta los campos de email y contraseña, valida errores y permite acceder
 * a las acciones principales: **login**, **registro** y **recuperar contraseña**.
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la capa **UI / Presentation** dentro del patrón **MVVM**.
 * - No contiene lógica de negocio: todo se comunica con el `ViewModel`
 *   mediante callbacks (`onEmailChange`, `onLoginClick`, etc.).
 * - La información del formulario (valores, errores, loading, etc.)
 *   se obtiene desde el estado `LoginUiState`, gestionado por el ViewModel.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Parámetros:
 * ----------------------------------------------------------------------------
 * @param state Estado de la UI (email, password, errores, loading, etc.)
 * @param onEmailChange Callback cuando el usuario escribe en el campo de email.
 * @param onPasswordChange Callback cuando el usuario escribe en el campo de contraseña.
 * @param onLoginClick Acción al pulsar "Entrar".
 * @param onRegisterClick Acción al pulsar "Crear cuenta".
 * @param onRecoverClick Acción al pulsar "¿Has olvidado la contraseña?".
 *
 * ----------------------------------------------------------------------------
 * 🔹 Buenas prácticas aplicadas:
 * ----------------------------------------------------------------------------
 * ✅ Arquitectura desacoplada: La UI no tiene lógica de autenticación.
 * ✅ Reutilizable: Puede probarse con datos mock sin depender del backend.
 * ✅ Reactiva: Usa `state` inmutable y callbacks unidireccionales.
 * ✅ Accesible: Indicadores visuales de error y estado de carga.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de integración:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * val vm: LoginViewModel = hiltViewModel()
 * val state by vm.state.collectAsState()
 *
 * LoginScreen(
 *     state = state,
 *     onEmailChange = vm::onEmailChange,
 *     onPasswordChange = vm::onPasswordChange,
 *     onLoginClick = vm::onLoginClick,
 *     onRegisterClick = vm::onRegisterClick,
 *     onRecoverClick = vm::onRecoverClick
 * )
 * ```
 * ----------------------------------------------------------------------------
 */
@Composable
fun LoginScreen(
    state: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val s = KivoFitTheme.spacing

    Box(
        modifier = modifier
            .fillMaxSize()
            // Padding del Scaffold/NavHost + nuestro spacing horizontal
            .padding(contentPadding)
            .padding(horizontal = s.xl),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(s.lg),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Inicia sesión",
                style = MaterialTheme.typography.headlineMedium
            )

            // 📨 Email
            OutlinedTextField(
                value = state.email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                isError = state.emailError != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )
            state.emailError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            // 🔑 Password
            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                label = { Text("Contraseña") },
                isError = state.passwordError != null,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )
            state.passwordError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            // Error de envío (backend / credenciales)
            state.submitError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // 🚀 Login
            Button(
                onClick = onLoginClick,
                enabled = state.isValid && !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading)
                    CircularProgressIndicator(strokeWidth = 2.dp)
                else
                    Text("Entrar")
            }

            // 🔁 Recuperar contraseña
            TextButton(
                onClick = onRecoverClick,
                enabled = !state.isLoading,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("¿Has olvidado la contraseña?")
            }

            // 🧾 Registro
            OutlinedButton(
                onClick = onRegisterClick,
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear cuenta")
            }
        }
    }
}
