package com.example.domain.model.repository

import com.example.domain.model.dto.Categories
import com.example.domain.model.dto.CategoriesResponse
import com.example.domain.model.dto.Dish

interface Repository {

    suspend fun getCategories(): CategoriesResponse
    suspend fun getDishes(): List<Dish>
    suspend fun addToCart(dish: Dish)
    suspend fun removeFromCart(dish: Dish)
    suspend fun increaseDishQuantity(dish: Dish)
    suspend fun decreaseDishQuantity(dish: Dish)
    suspend fun getCartTotalPrice(): Int
}