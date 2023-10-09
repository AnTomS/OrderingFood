package com.example.orderingfood.domain.repository


import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish


interface Repository {
    suspend fun getCategories(): List<Categories>?
    suspend fun getDishes(): List<Dish>?
    fun addToCart(dish: Dish)
    fun removeFromCart(dish: Dish)
    fun increaseDishQuantity(dish: Dish)
    fun decreaseDishQuantity(dish: Dish)
    fun getCartTotalPrice(): Int
    suspend fun getCartItems(): List<Pair<Dish, Int>>
    suspend fun getDishesById(dishId: Int): Dish?
}