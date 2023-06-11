package com.example.data.repository

import com.example.data.local.LocalDataSource
import com.example.data.local.RemoteDataSource
import com.example.domain.model.Categories
import com.example.domain.model.Dish

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getCategories(): List<Categories> {
        return try {
            val categories = remoteDataSource.getCategories()
            localDataSource.saveCategories(categories)
            categories
        } catch (e: Exception) {
            // Обработка ошибок, логирование и возврат пустого списка или другого значения по умолчанию
            emptyList()
        }
    }

    override suspend fun getDishes(): List<Dish> {
        return try {
            val dishes = remoteDataSource.getDishes()
            localDataSource.saveDishes(dishes)
            dishes
        } catch (e: Exception) {
            // Обработка ошибок, логирование и возврат пустого списка или другого значения по умолчанию
            emptyList()
        }
    }

    override suspend fun addToCart(dish: Dish) {
        localDataSource.addToCart(dish)
    }

    override suspend fun removeFromCart(dish: Dish) {
        localDataSource.removeFromCart(dish)
    }

    override suspend fun increaseDishQuantity(dish: Dish) {
        localDataSource.increaseDishQuantity(dish)
    }

    override suspend fun decreaseDishQuantity(dish: Dish) {
        localDataSource.decreaseDishQuantity(dish)
    }

    override suspend fun getCartTotalPrice(): Int {
        return localDataSource.getCartTotalPrice()
    }

}
