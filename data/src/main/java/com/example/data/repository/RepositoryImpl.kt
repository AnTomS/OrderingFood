package com.example.data.repository

import com.example.data.model.Categories
import com.example.data.model.Dish
import com.example.data.network.ApiServiceInterface
import retrofit2.await
import javax.inject.Inject

class RepositoryImpl
@Inject constructor(private val apiService: ApiServiceInterface) : Repository {
    override suspend fun getCategories(): List<Categories> {
        return try {
            apiService.getCategories().await()
        } catch (e: Exception) {

            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getDishes(): List<Dish> {
        return try {
            apiService.getDishes().await()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
