package com.KivoFit.domain.usecase.auth

import com.KivoFit.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * ----------------------------------------------------------------------------
 * RegisterUseCase.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este **caso de uso** representa la acción de **registrar un nuevo usuario**
 * en el sistema. Su misión es **encapsular la lógica de negocio** asociada al
 * proceso de registro, evitando que el ViewModel tenga que interactuar
 * directamente con el repositorio o la capa de datos.
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la **capa de dominio (Domain Layer)** en la arquitectura **Clean Architecture / MVVM**.
 * - Los *Use Cases* son clases centradas en una **única acción o proceso de negocio**.
 * - Permiten mantener el código de los ViewModels limpio, declarativo y enfocado solo en la UI.
 *
 * 🔹 Responsabilidad principal:
 * - Validar o limpiar los parámetros de entrada (por ejemplo, eliminar espacios).
 * - Delegar la operación de registro al `AuthRepository`.
 * - Retornar el resultado de la operación (`Result<Unit>`), que el ViewModel podrá interpretar.
 *
 * 🔹 Beneficios:
 * - Facilita el testeo unitario: puedes probar este caso de uso sin depender del ViewModel.
 * - Aísla la lógica de negocio del framework Android.
 * - Permite intercambiar fácilmente implementaciones de repositorio
 *   (por ejemplo, pasar de un `FakeAuthRepository` a un `RetrofitAuthRepository`).
 *
 * 🔹 Inyección de dependencias:
 * - `@Inject constructor(...)` permite que Hilt cree e inyecte automáticamente
 *   una instancia de `AuthRepository` cuando el caso de uso sea requerido por un ViewModel.
 *
 * 🔹 Operador `invoke`:
 * - Permite ejecutar el caso de uso como si fuera una función:
 *   ```kotlin
 *   val result = registerUseCase(email, password)
 *   ```
 *   en lugar de:
 *   ```kotlin
 *   val result = registerUseCase.invoke(email, password)
 *   ```
 * - Esto mejora la legibilidad del código y es una práctica habitual en proyectos profesionales.
 *
 * 🔹 Flujo de ejecución:
 * ```
 * RegisterViewModel
 *     ↓
 * RegisterUseCase
 *     ↓
 * AuthRepository
 *     ↓
 * FakeAuthRepository o AuthRepositoryImpl (según el entorno)
 * ```
 *
 * ----------------------------------------------------------------------------
 */
class RegisterUseCase @Inject constructor(
    private val repo: AuthRepository
) {

    /**
     * Ejecuta el proceso de registro de usuario delegando en el repositorio.
     *
     * @param email    Correo electrónico introducido por el usuario.
     * @param password Contraseña elegida por el usuario.
     * @return Un `Result<Unit>` indicando el resultado del proceso:
     *   - `Result.success(Unit)` → Registro exitoso.
     *   - `Result.failure(Exception)` → Fallo (email inválido, contraseña corta, etc.).
     *
     * Este método es `suspend` porque la operación suele implicar una acción asíncrona
     * como una llamada a un backend o una inserción en base de datos local.
     */
    suspend operator fun invoke(email: String, password: String): Result<Unit> =
        repo.register(email.trim(), password)
}
