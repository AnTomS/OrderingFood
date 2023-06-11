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
        TODO("Not yet implemented")
    }

    override suspend fun getDishes(): List<Dish> {
        TODO("Not yet implemented")
    }

    override suspend fun addToCart(dish: Dish) {
        // Реализация добавления блюда в корзину
    }

    override suspend fun removeFromCart(dish: Dish) {
        // Реализация удаления блюда из корзины
    }

    override suspend fun increaseDishQuantity(dish: Dish) {
        // Реализация увеличения количества блюда в корзине
    }

    override suspend fun decreaseDishQuantity(dish: Dish) {
        // Реализация уменьшения количества блюда в корзине
    }

    override suspend fun getCartTotalPrice(): Int {
        TODO("Not yet implemented")
    }

}
