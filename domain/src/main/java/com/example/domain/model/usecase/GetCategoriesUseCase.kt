package com.example.domain.model.usecase

import com.example.domain.model.dto.CategoriesResponse
import com.example.domain.model.repository.Repository

class GetCategoriesUseCase(
    private val repository: Repository
) {
    suspend fun execute(): CategoriesResponse {
        return repository.getCategories()
    }
}