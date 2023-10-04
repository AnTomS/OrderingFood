package com.example.orderingfood.domain.usecase

import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.domain.repository.Repository

class GetDishesByIdUseCase(private val repository: Repository, private val dishId: Int) {
    suspend fun execute(): Dish? {
        return repository.getDishesById(dishId)
    }
}