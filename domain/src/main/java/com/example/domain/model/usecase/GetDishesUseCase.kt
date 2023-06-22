package com.example.domain.model.usecase

import com.example.domain.model.dto.Dish
import com.example.domain.model.repository.Repository

class GetDishesUseCase(private val repository: Repository) {
    suspend fun execute(): List<Dish> {
        return repository.getDishes()
    }
}