package com.example.orderingfood.data.repository.datasource

import android.util.Log
import com.example.orderingfood.data.network.ApiServiceInterface
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish

class RemoveDataSource(private val apiService: ApiServiceInterface) {

    private val cartItems = mutableListOf<Pair<Dish, Int>>()
    private val cachedDishes: MutableMap<Int, Dish> = HashMap()
    private var cachedDishList: List<Dish> = emptyList()

    suspend fun getCategories(): List<Categories> {
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

    suspend fun getDishes(): List<Dish> {
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
}