package com.example.orderingfood.data.repository

import android.util.Log
import com.example.orderingfood.data.network.ApiServiceInterface
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl
@Inject constructor(private val apiService: ApiServiceInterface) : Repository {
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

//    override suspend fun getDishes(): List<Dish> {
//        return try {
//            val response = apiService.getDishes()
//            if (response.isSuccessful) {
//                val dishes = response.body()!!.dishes
//                Log.d("RepositoryImpl", "Dishes: $dishes")
//                dishes
//            } else {
//                Log.d("RepositoryImpl", "Unsuccessful response: ${response.code()}")
//                emptyList()
//            }
//        } catch (e: Exception) {
//            Log.d("RepositoryImpl", "Exception: ${e.message}")
//            emptyList()
//        }
//    }

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
