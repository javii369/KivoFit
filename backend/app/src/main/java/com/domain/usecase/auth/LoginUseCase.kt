package com.KivoFit.domain.usecase.auth

import com.KivoFit.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * ----------------------------------------------------------------------------
 * LoginUseCase.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este **caso de uso** encapsula la lógica de negocio relacionada con el proceso
 * de **inicio de sesión (login)** del usuario.
 *
 * Su misión es **intermediar entre la capa de presentación (ViewModel) y el repositorio**,
 * asegurando que el ViewModel no tenga que preocuparse por la lógica de autenticación.
 * Esto mantiene la **UI libre de lógica de negocio**, respetando los principios de
 * **Clean Architecture**.
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la **capa de dominio (Domain Layer)**.
 * - Los *use cases* definen **acciones de negocio concretas** y suelen ser *unitarios*:
 *   “Login”, “GetUserProfile”, “UpdatePassword”, etc.
 * - Cada uno se encarga de **una única responsabilidad** (principio *SRP* de SOLID).
 *
 * 🔹 Responsabilidades del caso de uso:
 * 1. Recibir las entradas del ViewModel (`email`, `password`).
 * 2. Aplicar validaciones o transformaciones simples (como `trim()`).
 * 3. Delegar la operación al repositorio (`AuthRepository`).
 * 4. Devolver un `Result<Unit>` que indica si la operación tuvo éxito o falló.
 *
 * 🔹 Por qué es útil:
 * - Permite **testear la lógica de negocio de forma aislada** (sin UI ni backend real).
 * - Facilita el **mantenimiento y escalabilidad**: si cambia la forma de login (por token, OAuth...),
 *   solo se modifica este caso de uso.
 * - Evita duplicar lógica en los ViewModels.
 *
 * 🔹 Inyección de dependencias:
 * - `@Inject` permite que Hilt cree automáticamente una instancia del caso de uso
 *   e inyecte el repositorio (`AuthRepository`) que necesita.
 *
 * 🔹 Operador `invoke`:
 * - Permite ejecutar el caso de uso como si fuera una función:
 *   ```kotlin
 *   val result = loginUseCase(email, password)
 *   ```
 *   En lugar de:
 *   ```kotlin
 *   val result = loginUseCase.invoke(email, password)
 *   ```
 * - Mejora la legibilidad en el ViewModel.
 *
 * 🔹 Flujo de ejecución:
 * ```
 * LoginViewModel
 *     ↓
 * LoginUseCase
 *     ↓
 * AuthRepository
 *     ↓
 * FakeAuthRepository o AuthRepositoryImpl
 * ```
 *
 * ----------------------------------------------------------------------------
 */
class LoginUseCase @Inject constructor(
    private val repo: AuthRepository
) {

    /**
     * Ejecuta el proceso de login delegando en el repositorio.
     *
     * @param email    Correo electrónico introducido por el usuario.
     * @param password Contraseña introducida por el usuario.
     * @return Un objeto `Result<Unit>` que indica el éxito o el fallo del inicio de sesión.
     *
     * - `Result.success(Unit)` → Login exitoso.
     * - `Result.failure(Exception)` → Error (credenciales inválidas, usuario no encontrado, etc.).
     *
     * Este método es `suspend` porque normalmente implica una operación asíncrona
     * (como una llamada a red o una consulta en base de datos).
     */
    suspend operator fun invoke(email: String, password: String): Result<Unit> =
        repo.login(email.trim(), password)
}
