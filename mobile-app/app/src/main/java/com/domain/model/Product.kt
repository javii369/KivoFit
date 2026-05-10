package com.KivoFit.domain.model

/**
 * ----------------------------------------------------------------------------
 * Product.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Esta clase representa el **modelo de dominio (Domain Model)** de un producto.
 * Es una entidad pura que forma parte del **Domain Layer** en una arquitectura
 * basada en **Clean Architecture + MVVM**.
 *
 * Un modelo de dominio define **la forma en que la aplicación entiende sus datos**,
 * independientemente de cómo se obtengan (API, base de datos local, mock, etc.).
 *
 * 🔹 Rol dentro de la arquitectura:
 * - En el **Domain Layer**, los modelos son **independientes** de cualquier
 *   tecnología o librería externa (sin Retrofit, sin Room, sin Compose…).
 * - Esto permite que la lógica de negocio y los casos de uso (Use Cases)
 *   trabajen con datos puros, sin acoplarse a cómo se almacenan o presentan.
 *
 * 🔹 Ventaja clave:
 * Si el origen de los datos cambia (por ejemplo, de una API REST a una base de datos local),
 * **no se necesita modificar el modelo de dominio**, solo el mapper que convierte
 * los datos entre capas.
 *
 * 🔹 Ejemplo de flujo:
 * 1. El repositorio obtiene datos de una API (`ProductDto` en la capa `data`).
 * 2. Esos datos se transforman en un `Product` (modelo de dominio).
 * 3. El ViewModel los expone a la UI.
 * 4. La UI los muestra con un `ProductUiModel` (si se usa una capa de presentación).
 *
 * 🔹 Campos:
 * @property id          Identificador único del producto (en formato texto).
 * @property name        Nombre comercial del producto.
 * @property description Breve descripción o detalles del producto.
 * @property price       Precio del producto en euros.
 * @property imageUrl    URL de la imagen asociada (se carga en la UI con Coil o similar).
 * ----------------------------------------------------------------------------
 */
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
