package com.KivoFit.ui.screens.auth.register

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
import com.KivoFit.ui.theme.KivoFitTheme

/**
 * ----------------------------------------------------------------------------
 * RegisterScreen.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Esta pantalla representa la **UI del flujo de registro de usuario**.
 * Permite introducir el email, la contraseña y su repetición, mostrando
 * los posibles errores de validación y el estado de carga (spinner).
 *
 * En términos arquitectónicos, **no contiene lógica de negocio**.
 * Toda la lógica y validación provienen del `RegisterViewModel` a través
 * del estado inmutable `RegisterUiState` y las funciones callback que se le pasan.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Contexto arquitectónico:
 * ----------------------------------------------------------------------------
 * - Capa: **Presentation / UI**
 * - Patrón: **MVVM (Model-View-ViewModel)**
 * - Relación directa con: `RegisterViewModel` y `RegisterEntry`
 *
 * Esta pantalla sigue el principio **UI → ViewModel → UseCase → Repository**
 * y nunca accede directamente a datos o lógica de dominio.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Parámetros:
 * ----------------------------------------------------------------------------
 * @param state Estado actual de la UI, gestionado por el ViewModel.
 *              Contiene campos de texto, errores y estado de carga.
 * @param onEmailChange Callback ejecutado al escribir en el campo de email.
 * @param onPasswordChange Callback ejecutado al escribir en el campo de contraseña.
 * @param onRepeatChange Callback ejecutado al escribir en el campo de repetición de contraseña.
 * @param onRegisterClick Acción principal al pulsar el botón "Crear cuenta".
 * @param onBackClick Acción al pulsar "Volver" (normalmente → Login).
 *
 * ----------------------------------------------------------------------------
 * 🔹 Buenas prácticas aplicadas:
 * ----------------------------------------------------------------------------
 * ✅ **Unidireccionalidad de datos**: la UI solo recibe estado y callbacks.
 * ✅ **Inmutabilidad del estado**: `RegisterUiState` no se modifica desde la UI.
 * ✅ **Reactividad**: Compose se recompone automáticamente ante cambios de estado.
 * ✅ **Separación de responsabilidades**: la validación y la navegación están fuera de la UI.
 * ✅ **Accesibilidad visual**: mensajes de error visibles bajo cada campo.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ciclo de flujo:
 * ----------------------------------------------------------------------------
 * 1️⃣ El ViewModel emite un nuevo estado (`RegisterUiState`).
 * 2️⃣ Compose detecta el cambio → `RegisterScreen` se recompone.
 * 3️⃣ El usuario escribe → la UI llama a `onEmailChange`, etc.
 * 4️⃣ El ViewModel actualiza el estado y revalida los datos.
 * 5️⃣ Si todo es correcto, `onRegisterClick` ejecuta el caso de uso de registro.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Diseño visual:
 * ----------------------------------------------------------------------------
 * - Usa un `Box` centrado con padding horizontal para mantener simetría.
 * - Contiene una `Column` con espaciado controlado por el Design System (`KivoFitTheme.spacing`).
 * - Cada `OutlinedTextField` muestra su mensaje de error debajo si aplica.
 * - Los botones mantienen el ancho completo (`fillMaxWidth`) por ergonomía.
 *
 * ----------------------------------------------------------------------------
 * 🔹 Ejemplo de uso:
 * ----------------------------------------------------------------------------
 * ```kotlin
 * val vm: RegisterViewModel = hiltViewModel()
 * val state by vm.state.collectAsState()
 *
 * RegisterScreen(
 *     state = state,
 *     onEmailChange = vm::onEmailChange,
 *     onPasswordChange = vm::onPasswordChange,
 *     onRepeatChange = vm::onRepeatChange,
 *     onRegisterClick = vm::onRegisterClick,
 *     onBackClick = vm::onBackClick
 * )
 * ```
 * ----------------------------------------------------------------------------
 */
@Composable
fun RegisterScreen(
    state: RegisterUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val s = KivoFitTheme.spacing // Sistema de espaciado global (Design System)

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
            // 🧾 Título principal
            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.headlineMedium
            )

            // 📨 Campo de Email
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

            // 🔒 Campo de Contraseña
            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                label = { Text("Contraseña") },
                isError = state.passwordError != null,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth()
            )
            state.passwordError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            // 🔁 Repetir Contraseña
            OutlinedTextField(
                value = state.repeat,
                onValueChange = onRepeatChange,
                label = { Text("Repite la contraseña") },
                isError = state.repeatError != null,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth()
            )
            state.repeatError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            // 🚫 Error general (por ejemplo, usuario ya existente)
            state.submitError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // 🚀 Botón principal de registro
            Button(
                onClick = onRegisterClick,
                enabled = state.isValid && !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading)
                    CircularProgressIndicator(strokeWidth = 2.dp)
                else
                    Text("Crear cuenta")
            }

            // 🔙 Botón de volver al login
            TextButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Volver")
            }
        }
    }
}
