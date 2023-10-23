package com.example.orderingfood.domain.repository


import com.example.orderingfood.data.room.entity.DishesEntity
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish


interface Repository {
    suspend fun getCategories(): Result<List<Categories>>
    suspend fun getDishes(): Result<List<Dish>>?
    fun addToCart(dish: Dish)
    fun removeFromCart(dish: Dish)
    fun increaseDishQuantity(dish: Dish)
    fun decreaseDishQuantity(dish: Dish)
    fun getCartTotalPrice(): Int
    suspend fun getCartItems(): List<Pair<Dish, Int>>
    suspend fun getDishesById(dishId: Int): DishesEntity?
    suspend fun getDishesByTag(tag: List<String>): List<DishesEntity>
}