package com.example.orderingfood.data.repository

import android.util.Log
import com.example.orderingfood.data.repository.datasource.LocalDataSource
import com.example.orderingfood.data.repository.datasource.RemoveDataSource
import com.example.orderingfood.data.room.entity.DishesEntity
import com.example.orderingfood.data.room.entity.toDto
import com.example.orderingfood.data.room.entity.toEntity
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish
import com.example.orderingfood.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl
@Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoveDataSource
) : Repository {
    private val cartItems = mutableListOf<Pair<Dish, Int>>()
    private val cachedDishes: MutableMap<Int, Dish> = HashMap()
    private var cachedDishList: List<Dish> = emptyList()
    override suspend fun getCategories(): Result<List<Categories>> {
        val localCategories = localDataSource.getCategories()
        Log.d("RepositoryImpl", "Local categories: $localCategories")
        if (localCategories.isNotEmpty()) {
            return Result.success(localCategories.toDto())
        }
        return try {
            val remoteCategories = remoteDataSource.getCategories()
            localDataSource.insertCategoriesToBd(remoteCategories.toEntity())
            Log.d("RepositoryImpl", "Remote categories: $remoteCategories")
            Result.success(remoteCategories)
        } catch (e: Exception) {
            Log.e("RepositoryImpl", "Error loading categories: ${e.message}", e)
            Result.failure(exception = e)

        }
    }

    override suspend fun getDishes(): Result<List<Dish>> {
        val localDishes = localDataSource.getAllDishes()
        Log.d("RepositoryImpl", "Local dishes: $localDishes")
        if (localDishes.isNotEmpty()) {
            return Result.success(localDishes.toDto())
        }
        return try {
            val remoteDishes = remoteDataSource.getDishes()
            localDataSource.insertDishesToDb(remoteDishes.toEntity())
            Log.d("RepositoryImpl", "Remote dishes: $remoteDishes")
            Result.success(remoteDishes)
        } catch (e: Exception) {
            Log.e("RepositoryImpl", "Error loading dishes: ${e.message}", e)
            Result.failure(exception = e)
        }
    }

    override suspend fun getDishesById(dishId: Int): DishesEntity? {
        val liveData = localDataSource.getDishesById(dishId)
        return liveData.value
    }

    override suspend fun getDishesByTag(tag: List<String>): List<DishesEntity> {
        return localDataSource.detDishesByTag(tag)
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

    override fun removeFromCart(dish: Dish) {
        cartItems.removeAll { it.first.id == dish.id }
    }

    override fun increaseDishQuantity(dish: Dish) {
        val existingItem = cartItems.find { it.first.id == dish.id }
        existingItem?.let {
            val updatedItem = it.copy(second = it.second + 1)
            cartItems[cartItems.indexOf(it)] = updatedItem
        }
    }

    override fun decreaseDishQuantity(dish: Dish) {
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

    override fun getCartTotalPrice(): Int {
        return cartItems.sumOf { it.first.price * it.second }

    }

    override suspend fun getCartItems(): List<Pair<Dish, Int>> {
        return cartItems.toList()
    }

}
