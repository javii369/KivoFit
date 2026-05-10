package com.KivoFit.data.repository

import com.KivoFit.domain.model.Product
import com.KivoFit.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.delay

/**
 * ----------------------------------------------------------------------------
 * FakeProductRepository.kt
 * ----------------------------------------------------------------------------
 *
 * 🔹 Descripción general:
 * Implementación **falsa (fake)** del repositorio de productos.
 * Sirve para **simular la obtención de datos desde un servidor remoto**,
 * permitiendo desarrollar y probar la interfaz de usuario sin depender
 * de un backend real o de una base de datos.
 *
 * 🔹 Rol dentro de la arquitectura:
 * Forma parte de la **capa de datos (data layer)** en una arquitectura **MVVM + Clean Architecture**.
 * Implementa la interfaz `ProductRepository` definida en la capa de dominio,
 * lo que permite cambiar esta implementación "fake" por una versión real
 * (por ejemplo, una que use Retrofit o Room) sin tocar la lógica del ViewModel.
 *
 * 🔹 Inyección de dependencias:
 * - `@Singleton` garantiza que haya una sola instancia en toda la aplicación.
 * - `@Inject constructor()` permite que Hilt cree e inyecte el repositorio automáticamente.
 *
 * 🔹 Lógica actual:
 * Simula una llamada de red con `delay(600)` milisegundos,
 * luego devuelve una lista de 100 productos generados dinámicamente.
 *
 * Cada producto incluye:
 *  - ID único (1 a 100)
 *  - Nombre y descripción simulados
 *  - Precio aleatorio entre 10.99€ y 99.99€
 *  - Imagen de muestra usando el servicio público **Picsum Photos**
 *    (`https://picsum.photos/seed/...`) con una *seed* estable para obtener
 *    siempre la misma imagen para un mismo producto.
 *
 * 🔹 Uso práctico:
 * Este repositorio es ideal para:
 *  - **Prototipar interfaces de usuario.**
 *  - **Probar la navegación y el renderizado de listas.**
 *  - **Simular estados de carga, éxito y error.**
 *
 * 🔹 Próximos pasos:
 * Sustituirlo en producción por una implementación real de `ProductRepository`
 * que obtenga datos desde una API REST o una base de datos local (Room).
 * ----------------------------------------------------------------------------
 */
@Singleton
class FakeProductRepository @Inject constructor() : ProductRepository {

    /**
     * Simula la obtención de productos desde un backend.
     *
     * 🧠 Concepto:
     * `delay(600)` imita la latencia de red típica al consumir una API real.
     * Después, generamos datos ficticios consistentes para que la UI se vea realista.
     *
     * @return Una lista de 100 productos simulados, con imágenes y precios aleatorios.
     */
    override suspend fun getProducts(): List<Product> {
        // Simula una llamada remota con un retardo de ~0.6 segundos
        delay(600)

        // Genera una lista de productos con imágenes estables de Picsum
        return (1..100).map { i ->
            Product(
                id = i.toString(),
                name = "Producto $i",
                description = "Descripción breve del producto $i. Calidad top.",
                price = (10..99).random() + 0.99,
                imageUrl = "https://picsum.photos/seed/product_$i/600/400"
            )
        }
    }
}
