package com.example.orderingfood.data.repository

import android.util.Log
import com.example.orderingfood.data.network.ApiServiceInterface
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl
@Inject constructor(private val apiService: ApiServiceInterface) : Repository {
    private val cartItems = mutableListOf<Pair<Dish, Int>>()
    private val cachedDishes: MutableMap<Int, Dish> = HashMap()
    private var cachedDishList: List<Dish> = emptyList()
    override suspend fun getCategories(): List<Categories> {
        return try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                val categories = response.body()!!.сategories
                Log.d("CategoryList", "Loaded categories: $categories")
                categories
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getDishes(): List<Dish> {
        return try {
            val response = apiService.getDishes()
            if (response.isSuccessful) {
                cachedDishList = response.body()?.dishes ?: emptyList()
                Log.d("RepositoryImpl", "Dishes: $cachedDishList")
                cachedDishes.clear()
                cachedDishList.forEach { dish ->
                    cachedDishes[dish.id] = dish
                }
                cachedDishList
            } else {
                Log.d("RepositoryImpl", "Unsuccessful response: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.d("RepositoryImpl", "Exception: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getDishesById(dishId: Int): Dish? {
        return cachedDishes[dishId]
    }


    override fun addToCart(dish: Dish) {
        val existingItem = cartItems.find { it.first.id == dish.id }
        if (existingItem != null) {
            // Увеличиваем количество, если блюдо уже в корзине
            increaseDishQuantity(dish)
        } else {
            // Добавляем новое блюдо в корзину
            cartItems.add(dish to 1)
        }
    }

    override suspend fun removeFromCart(dish: Dish) {
        cartItems.removeAll { it.first.id == dish.id }
    }

    override  fun increaseDishQuantity(dish: Dish) {
        val existingItem = cartItems.find { it.first.id == dish.id }
        existingItem?.let {
            val updatedItem = it.copy(second = it.second + 1)
            cartItems[cartItems.indexOf(it)] = updatedItem
        }
    }

    override suspend fun decreaseDishQuantity(dish: Dish) {
        val existingItem = cartItems.find { it.first.id == dish.id }
        existingItem?.let {
            val updatedItem = it.copy(second = it.second - 1)
            if (updatedItem.second > 0) {
                cartItems[cartItems.indexOf(it)] = updatedItem
            } else {
                // Если количество стало нулевым, удалить блюдо из корзины
                removeFromCart(dish)
            }
        }
    }

    override suspend fun getCartTotalPrice(): Int {
        return cartItems.sumOf { it.first.price * it.second }

    }

    override suspend fun getCartItems(): List<Pair<Dish, Int>> {
        return cartItems.toList()
    }


}
