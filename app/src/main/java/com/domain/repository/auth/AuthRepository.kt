package com.KivoFit.domain.repository.auth

/**
 * ----------------------------------------------------------------------------
 * AuthRepository.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Esta interfaz define el **contrato del repositorio de autenticación (Auth Repository)**.
 * Su propósito es declarar todas las operaciones relacionadas con la gestión
 * de usuarios y credenciales (login, registro, recuperación de contraseña, etc.)
 * sin importar **cómo o dónde** se implementen.
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la **capa de dominio (Domain Layer)** dentro de la arquitectura
 *   **Clean Architecture / MVVM**.
 * - El dominio contiene **solo lógica de negocio y abstracciones puras**,
 *   sin dependencias de frameworks (como Retrofit, Room o Firebase).
 *
 * 🔹 Principios aplicados:
 * - **Inversión de dependencias (D de SOLID):**
 *   Las clases de alto nivel (UseCases, ViewModels) dependen de **interfaces**,
 *   no de implementaciones concretas.
 * - Esto permite **cambiar fácilmente la fuente de datos** (API, mock, DB local)
 *   sin tocar el resto de la aplicación.
 *
 * 🔹 Ejemplos de implementación:
 * - En desarrollo → `FakeAuthRepository` (datos simulados).
 * - En producción → `AuthRepositoryImpl` (por ejemplo, usando Retrofit o Firebase Auth).
 *
 * ----------------------------------------------------------------------------
 */
interface AuthRepository {

    /**
     * 🔐 Inicia sesión con un usuario existente.
     *
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return Un `Result<Unit>` que indica si la autenticación fue exitosa o fallida.
     *
     * Ejemplo de uso:
     * ```kotlin
     * val result = authRepository.login(email, password)
     * if (result.isSuccess) { ... } else { ... }
     * ```
     *
     * Este método es `suspend` porque normalmente implica una operación asíncrona:
     * - Llamada de red a una API externa.
     * - Validación en un servicio remoto.
     */
    suspend fun login(email: String, password: String): Result<Unit>

    /**
     * 📧 Envía un correo de recuperación de contraseña al usuario.
     *
     * @param email Correo electrónico asociado a la cuenta del usuario.
     * @return Un `Result<Unit>` que indica si la solicitud se envió correctamente.
     *
     * Ejemplo de uso:
     * ```kotlin
     * val result = authRepository.recoverPassword("user@mail.com")
     * if (result.isSuccess) { showSuccess() } else { showError() }
     * ```
     *
     * Normalmente este método:
     * - Valida que el email exista en la base de datos.
     * - Genera un token temporal o un enlace de recuperación.
     * - Envía el correo mediante un servicio SMTP o API (SendGrid, Firebase, etc.).
     *
     * En una versión *fake*, simplemente devuelve `Result.success(Unit)`
     * después de simular una pequeña latencia.
     */
    suspend fun recoverPassword(email: String): Result<Unit>

    /**
     * 🧾 Registra un nuevo usuario en el sistema.
     *
     * @param email    Correo electrónico del nuevo usuario.
     * @param password Contraseña que se almacenará de forma segura (normalmente hasheada).
     * @return Un `Result<Unit>` que indica si el registro fue exitoso o fallido.
     *
     * Ejemplo de uso:
     * ```kotlin
     * val result = authRepository.register("newuser@mail.com", "password123")
     * if (result.isSuccess) { navigateToLogin() } else { showError() }
     * ```
     *
     * En un entorno real:
     * - Valida el formato del email y la fortaleza de la contraseña.
     * - Crea un nuevo usuario en la base de datos o API.
     * - Puede devolver errores específicos (usuario existente, email inválido, etc.).
     *
     * En un entorno *fake*, simplemente simula el proceso y devuelve `Result.success(Unit)`.
     */
    suspend fun register(email: String, password: String): Result<Unit>
}
