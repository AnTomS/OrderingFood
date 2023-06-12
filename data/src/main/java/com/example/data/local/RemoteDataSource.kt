package com.example.data.local

import android.util.Log
import com.example.data.network.ApiServiceInterface
import com.example.domain.model.Categories
import com.example.domain.model.Dish
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiServiceInterface) {

    suspend fun getCategories(): List<Categories> {
        return try {
            val response = apiService.getCategories().execute()
            if (response.isSuccessful) {
                val categories = response.body()?.categories ?: emptyList()
                Log.d("RemoteDataSource", "Categories: $categories")
                categories
            } else {
                Log.d("RemoteDataSource", "Unsuccessful response: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.d("RemoteDataSource", "Exception: ${e.message}")
            emptyList()
        }
    }

    fun getDishes(): List<Dish> {
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