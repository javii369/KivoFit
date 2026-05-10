package com.KivoFit.di

import com.KivoFit.domain.repository.auth.AuthRepository
import com.KivoFit.data.repository.FakeAuthRepository
import com.KivoFit.data.repository.FakeProductRepository
import com.KivoFit.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ----------------------------------------------------------------------------
 * AppModule.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Este archivo define un **módulo de inyección de dependencias (Dependency Injection Module)**.
 * Su propósito es decirle a **Hilt** qué implementación concreta debe usar cuando
 * se solicite una interfaz en cualquier parte del proyecto.
 *
 * 🔹 Rol dentro de la arquitectura:
 * Pertenece a la **capa `di/` (Dependency Injection)**, encargada de conectar:
 *  - Las **interfaces de dominio (Domain Layer)** → definidas como contratos.
 *  - Con las **implementaciones concretas (Data Layer)** → repositorios reales o “fake”.
 *
 * Gracias a este módulo, el código del ViewModel o los UseCases **no necesitan saber**
 * qué clase específica se está usando.
 * Solo conocen la interfaz → lo que mantiene el sistema desacoplado y fácilmente reemplazable.
 *
 * 🔹 Principios aplicados:
 * - **Inversión de dependencias (D - SOLID):**
 *   Las capas superiores dependen de abstracciones (interfaces), no de implementaciones.
 * - **Open/Closed principle:**
 *   Podemos añadir nuevas implementaciones sin tocar el código que las usa.
 *
 * 🔹 Anotaciones clave:
 * - `@Module`: indica a Hilt que esta clase define cómo construir dependencias.
 * - `@InstallIn(SingletonComponent::class)`:
 *   Especifica que las dependencias declaradas en este módulo estarán disponibles
 *   durante **todo el ciclo de vida de la aplicación** (Singleton scope).
 * - `@Binds`: se usa en funciones abstractas para decir:
 *   “Cuando alguien pida una interfaz X, inyecta esta implementación Y”.
 * - `@Singleton`: garantiza que todas las inyecciones de ese tipo compartirán
 *   **la misma instancia global** (ideal para repositorios).
 *
 * 🔹 Ejemplo de flujo de inyección:
 * 1. Un `LoginUseCase` necesita un `AuthRepository`.
 * 2. Hilt busca en los módulos registrados cómo crearlo.
 * 3. Encuentra este `AppModule`, ve el `@Binds` → y crea un `FakeAuthRepository`.
 * 4. Esa instancia se inyecta automáticamente en el `UseCase`.
 *
 * 🔹 Ventaja:
 * Si en un futuro tienes una clase real `AuthRepositoryImpl`, solo tienes que cambiar
 * el binding aquí, y toda la aplicación pasará a usar la nueva implementación
 * sin modificar ni un solo ViewModel o UseCase.
 * ----------------------------------------------------------------------------
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    /**
     * 🔐 Enlace entre `AuthRepository` (interfaz de dominio)
     * y `FakeAuthRepository` (implementación de datos simulada).
     *
     * Siempre que una clase pida un `AuthRepository`, Hilt le proporcionará
     * una instancia de `FakeAuthRepository`.
     *
     * @param impl Implementación concreta que se inyectará.
     * @return Una instancia gestionada por Hilt como `AuthRepository`.
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: FakeAuthRepository
    ): AuthRepository

    /**
     * 🏪 Enlace entre `ProductRepository` (interfaz de dominio)
     * y `FakeProductRepository` (implementación simulada).
     *
     * Este binding se usa para inyectar datos mock en pantallas como Home,
     * sin depender de una API real.
     *
     * En producción, este binding podría reemplazarse por una clase como:
     * `RemoteProductRepository` o `ProductRepositoryImpl`.
     */
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: FakeProductRepository
    ): ProductRepository
}
