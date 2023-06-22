package com.example.data.repository

import com.example.data.network.ApiServiceInterface
import com.example.domain.model.dto.CategoriesResponse
import com.example.domain.model.dto.Dish
import com.example.domain.model.repository.Repository


class RepositoryImpl(
    private val apiServiceInterface: ApiServiceInterface
) : Repository {
    override suspend fun getCategories(): CategoriesResponse {
        return apiServiceInterface.getCategories()
    }

    override suspend fun getDishes(): List<Dish> {
        return apiServiceInterface.getDishes()
    }

    override suspend fun addToCart(dish: Dish) {
        TODO()
    }

    override suspend fun removeFromCart(dish: Dish) {
        TODO()
    }

    override suspend fun increaseDishQuantity(dish: Dish) {
        TODO()
    }

    override suspend fun decreaseDishQuantity(dish: Dish) {
        TODO()
    }

    override suspend fun getCartTotalPrice(): Int {
        TODO()
    }

}
