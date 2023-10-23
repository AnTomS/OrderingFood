package com.example.orderingfood.domain.usecase

import com.example.orderingfood.data.room.entity.DishesEntity
import com.example.orderingfood.domain.repository.Repository

class GetDishesByIdUseCase(private val repository: Repository, private val dishId: Int) {
    suspend fun execute(): DishesEntity? {
        return repository.getDishesById(dishId)
    }
}