package com.example.data.local

import com.example.data.network.ApiServiceInterface
import com.example.domain.model.Categories
import com.example.domain.model.Dish

class RemoteDataSource(private val apiService: ApiServiceInterface) {

    suspend fun getCategories(): List<Categories> {
        return try {
            val response = apiService.getCategories().execute()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDishes(): List<Dish> {
        return try {
            val response = apiService.getDishes().execute()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}