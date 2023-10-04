package com.example.orderingfood.domain.repository


import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish


interface Repository {
    suspend fun getCategories(): List<Categories>?
    suspend fun getDishes(): List<Dish>?
    suspend fun addToCart(dish: Dish)
    suspend fun removeFromCart(dish: Dish)
    suspend fun increaseDishQuantity(dish: Dish)
    suspend fun decreaseDishQuantity(dish: Dish)
    suspend fun getCartTotalPrice(): Int
    suspend fun getDishesById(dishId: Int): Dish?
}