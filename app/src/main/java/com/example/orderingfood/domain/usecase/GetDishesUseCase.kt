package com.example.orderingfood.domain.usecase


import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.domain.repository.Repository

class GetDishesUseCase(private val repository: Repository) {
    suspend fun execute(): Result<List<Dish>>? {
        return repository.getDishes()
    }
}