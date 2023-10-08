package com.example.orderingfood.domain.repository


import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish


interface Repository {
    suspend fun getCategories(): List<Categories>?
    suspend fun getDishes(): List<Dish>?
     fun addToCart(dish: Dish)
    suspend fun removeFromCart(dish: Dish)
     fun increaseDishQuantity(dish: Dish)
    suspend fun decreaseDishQuantity(dish: Dish)
    suspend fun getCartTotalPrice(): Int
    suspend fun getCartItems(): List<Pair<Dish, Int>>
    suspend fun getDishesById(dishId: Int): Dish?
}