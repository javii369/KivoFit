package com.KivoFit.domain.repository

import com.KivoFit.domain.model.Product

/**
 * ----------------------------------------------------------------------------
 * ProductRepository.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Esta interfaz define el **contrato del repositorio de productos (Product Repository)**.
 * Su función es declarar las operaciones que la aplicación puede realizar sobre los productos,
 * sin importar **cómo o desde dónde** se obtienen los datos (API, base de datos local, mock, etc.).
 *
 * 🔹 Contexto arquitectónico:
 * - Pertenece a la **capa de dominio (Domain Layer)** dentro del patrón
 *   **Clean Architecture / MVVM**.
 * - Define las **abstracciones de negocio**, no las implementaciones.
 * - Los casos de uso (`UseCases`) y los ViewModels interactúan con esta interfaz
 *   sin conocer los detalles de infraestructura.
 *
 * 🔹 Principios aplicados:
 * - **Inversión de dependencias (D de SOLID)**:
 *   El dominio depende de abstracciones, no de implementaciones concretas.
 * - **Desacoplamiento total**:
 *   Si mañana cambias de Retrofit a Room o a una API GraphQL,
 *   ninguna capa superior necesita modificarse.
 *
 * 🔹 Ejemplo de flujo de dependencias:
 * 1. El ViewModel llama al caso de uso `GetProductsUseCase`.
 * 2. El caso de uso pide los datos a `ProductRepository`.
 * 3. Hilt inyecta una implementación concreta (por ejemplo, `FakeProductRepository`).
 * 4. Se devuelven los productos al ViewModel, listos para mostrar en la UI.
 *
 * 🔹 Implementaciones posibles:
 * - `FakeProductRepository`: Datos simulados para desarrollo o testing.
 * - `RemoteProductRepository`: Datos reales obtenidos de una API REST (Retrofit).
 * - `LocalProductRepository`: Datos almacenados en una base de datos local (Room).
 *
 * ----------------------------------------------------------------------------
 */
interface ProductRepository {

    /**
     * 🛒 Obtiene la lista de productos disponibles.
     *
     * @return Una lista de objetos `Product` con toda la información necesaria
     *         para mostrar en la interfaz de usuario.
     *
     * Este método es `suspend` porque normalmente implica una operación de I/O:
     * - Una llamada HTTP a un servidor remoto.
     * - Una lectura desde base de datos local.
     *
     * En implementaciones *fake*, se puede simular un retardo con `delay()`
     * para imitar la latencia de red.
     *
     * Ejemplo de uso:
     * ```kotlin
     * val products = productRepository.getProducts()
     * products.forEach { println(it.name) }
     * ```
     */
    suspend fun getProducts(): List<Product>
}
