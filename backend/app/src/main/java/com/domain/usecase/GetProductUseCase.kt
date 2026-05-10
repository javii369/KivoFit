package com.KivoFit.domain.usecase

import com.KivoFit.domain.model.Product
import com.KivoFit.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repo: ProductRepository
) {
    suspend operator fun invoke(): List<Product> = repo.getProducts()
}
