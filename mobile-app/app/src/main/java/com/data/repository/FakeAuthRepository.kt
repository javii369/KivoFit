package com.KivoFit.data.repository

import com.KivoFit.domain.repository.auth.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ----------------------------------------------------------------------------
 * FakeAuthRepository.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Implementación **falsa (fake)** del repositorio de autenticación.
 * Se usa para **simular un backend** mientras se desarrolla la app o se escriben tests.
 * Permite ejecutar la aplicación sin depender de una API real.
 *
 * 🔹 Rol en la arquitectura:
 * Pertenece a la **capa de datos (Data Layer)** dentro del patrón **Clean Architecture + MVVM**.
 * Implementa la interfaz `AuthRepository` definida en el dominio,
 * lo que garantiza que los casos de uso (`UseCases`) o los ViewModels
 * puedan comunicarse con un repositorio **sin importar si es real o simulado**.
 *
 * 🔹 En producción:
 * Esta clase se reemplazaría por un `RemoteAuthRepository` que hable con un backend
 * (por ejemplo, a través de Retrofit y una API REST).
 *
 * 🔹 Inyección de dependencias:
 * - `@Singleton`: garantiza una única instancia en toda la app.
 * - `@Inject constructor()`: permite a Hilt inyectarla automáticamente cuando se pida un `AuthRepository`.
 *
 * 🔹 Datos simulados:
 * Para fines de prueba, se considera un único usuario válido:
 *   📧 Email: admin@ceac.com
 *   🔑 Password: 1234
 *
 * Cualquier otra combinación devolverá error.
 * ----------------------------------------------------------------------------
 */
@Singleton
class FakeAuthRepository @Inject constructor() : AuthRepository {

    /**
     * Simula una llamada de login al backend.
     *
     * 🧠 Concepto clave:
     * El uso de `Result<Unit>` permite comunicar éxito o error sin lanzar excepciones directamente.
     *
     * @param email    Correo electrónico introducido por el usuario.
     * @param password Contraseña introducida por el usuario.
     * @return Un objeto `Result<Unit>` que representa éxito o error de autenticación.
     */
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (email == "admin@ceac.com" && password == "1234") {
            // ✅ Login simulado exitoso.
            Result.success(Unit)
        } else {
            // ❌ Error simulado: credenciales incorrectas.
            Result.failure(Exception("Credenciales incorrectas"))
        }
    }

    /**
     * Simula la recuperación de contraseña.
     *
     * 🧠 Concepto:
     * En un backend real, este método enviaría un correo electrónico con un enlace o código.
     * Aquí simplemente comprobamos si el email pertenece al dominio @ceac.com.
     *
     * - `delay(800)` imita el tiempo de espera de una petición HTTP (~0.8 segundos).
     *
     * @param email Correo electrónico al que se intentará enviar el enlace de recuperación.
     * @return Éxito si el email termina en "@ceac.com", fallo en caso contrario.
     */
    override suspend fun recoverPassword(email: String): Result<Unit> {
        delay(800)
        return if (email.endsWith("@ceac.com")) {
            // ✅ Simulamos un envío correcto del email de recuperación.
            Result.success(Unit)
        } else {
            // ❌ Simulamos un error si el email no pertenece al dominio válido.
            Result.failure(Exception("No existe ninguna cuenta con ese email"))
        }
    }

    /**
     * Simula el registro de un nuevo usuario.
     *
     * 🧠 Concepto:
     * En una app real este método haría:
     * - Llamadas POST a la API.
     * - Validación del email en base de datos.
     * - Hash de contraseñas.
     *
     * Aquí simplemente comprobamos reglas básicas y devolvemos un `Result` simulado.
     *
     * @param email Email del nuevo usuario.
     * @param password Contraseña elegida.
     * @return Éxito si pasa las validaciones, o fallo con mensaje descriptivo.
     */
    override suspend fun register(email: String, password: String): Result<Unit> {
        delay(800) // Simula una llamada remota (latencia de red)
        return when {
            // ❌ Email no pertenece al dominio válido.
            !email.endsWith("@ceac.com") ->
                Result.failure(Exception("Solo aceptamos emails @ceac.com en el mock"))

            // ❌ Contraseña demasiado corta.
            password.length < 6 ->
                Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))

            // ✅ Registro exitoso simulado.
            else -> Result.success(Unit)
        }
    }
}
