package com.example.orderingfood.domain.usecase

import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.repository.Repository

class GetCategoriesUseCase(
    private val repository: Repository
) {
    suspend fun execute(): List<Categories>? {
        return repository.getCategories()
    }
}