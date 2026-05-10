package com.KivoFit.domain.usecase.auth

import com.KivoFit.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * ----------------------------------------------------------------------------
 * RecoverPasswordUseCase.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este **caso de uso** encapsula la lógica de negocio asociada al proceso de
 * **recuperación de contraseña (password recovery)**.
 *
 * Su responsabilidad es **aislar la lógica de recuperación** del resto de la app,
 * de modo que el ViewModel no tenga que comunicarse directamente con el repositorio.
 * Esto permite mantener la UI simple, declarativa y sin reglas de negocio.
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la **capa de dominio (Domain Layer)** dentro de la arquitectura
 *   **Clean Architecture / MVVM**.
 * - En esta capa se ubican los **casos de uso (Use Cases)**, que representan
 *   acciones específicas del negocio, independientes de frameworks o librerías externas.
 *
 * 🔹 Responsabilidad principal:
 * - Recibir un email del ViewModel.
 * - Realizar una validación o preprocesamiento si es necesario (por ejemplo, `trim()`).
 * - Delegar la acción al repositorio (`AuthRepository.recoverPassword()`).
 * - Devolver el resultado (`Result<Unit>`) al ViewModel.
 *
 * 🔹 Beneficios:
 * - El ViewModel no necesita saber cómo se envía el correo ni si es un mock o una API real.
 * - Facilita el testeo y mantenimiento del código.
 * - Permite reemplazar la fuente de datos sin cambiar la capa de presentación.
 *
 * 🔹 Inyección de dependencias:
 * - `@Inject constructor(...)` permite que Hilt inyecte automáticamente el `AuthRepository`
 *   necesario para ejecutar el caso de uso.
 *
 * 🔹 Operador `invoke`:
 * - Permite usar el caso de uso como una función:
 *   ```kotlin
 *   val result = recoverPasswordUseCase(email)
 *   ```
 *   en lugar de `recoverPasswordUseCase.invoke(email)`.
 * - Mejora la legibilidad en el ViewModel.
 *
 * 🔹 Flujo de ejecución:
 * ```
 * RecoverPasswordViewModel
 *     ↓
 * RecoverPasswordUseCase
 *     ↓
 * AuthRepository
 *     ↓
 * FakeAuthRepository o AuthRepositoryImpl
 * ```
 *
 * ----------------------------------------------------------------------------
 */
class RecoverPasswordUseCase @Inject constructor(
    private val repo: AuthRepository
) {

    /**
     * Ejecuta el proceso de recuperación de contraseña.
     *
     * @param email Correo electrónico del usuario que solicita la recuperación.
     * @return Un `Result<Unit>` que representa el resultado del intento de recuperación.
     *
     * - `Result.success(Unit)` → Correo enviado correctamente.
     * - `Result.failure(Exception)` → Error (email no registrado, error de red, etc.).
     *
     * Este método es `suspend` porque normalmente implica una operación asíncrona
     * (como una llamada HTTP a un servicio externo o una consulta a una base de datos).
     */
    suspend operator fun invoke(email: String): Result<Unit> =
        repo.recoverPassword(email.trim())
}
