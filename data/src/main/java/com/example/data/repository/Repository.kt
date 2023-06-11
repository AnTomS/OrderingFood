package com.example.data.repository

import com.example.domain.model.Categories
import com.example.domain.model.Dish

interface Repository {

    suspend fun getCategories(): List<Categories>
    suspend fun getDishes(): List<Dish>
    suspend fun addToCart(dish: Dish)
    suspend fun removeFromCart(dish: Dish)
    suspend fun increaseDishQuantity(dish: Dish)
    suspend fun decreaseDishQuantity(dish: Dish)
    suspend fun getCartTotalPrice(): Int
}