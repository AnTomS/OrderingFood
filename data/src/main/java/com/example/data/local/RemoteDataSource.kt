package com.example.data.local

import android.util.Log
import com.example.data.network.ApiServiceInterface
import com.example.domain.model.dto.Categories
import com.example.domain.model.dto.CategoriesResponse
import com.example.domain.model.dto.Dish
import com.squareup.moshi.Moshi
import javax.inject.Inject

class RemoteDataSource () {

}

//    private val moshi: Moshi = MoshiProvider.moshi
//    suspend fun getCategories(): List<Categories> {
//        return try {
//            val response = apiService.getCategories().execute()
//            if (response.isSuccessful) {
//                val categories = response.body()?.categories ?: emptyList()
//                Log.d("RemoteDataSource", "Categories: $categories")
//                categories
//            } else {
//                Log.d("RemoteDataSource", "Unsuccessful response: ${response.code()}")
//                emptyList()
//            }
//        } catch (e: Exception) {
//            Log.d("RemoteDataSource", "Exception: ${e.message}")
//            emptyList()
//        }
//    }

//    suspend fun getCategories(): List<Categories> {
//        return try {
//            val response = apiService.getCategories().execute()
//            if (response.isSuccessful) {
//                val responseBody = response.body()?.toString()
//                Log.d("RemoteDataSource", "Categories: $responseBody")
//                handleJSONResponse(responseBody)
//            } else {
//                // Обработка ошибки
//                Log.d("RemoteDataSource", "неудачный запрос: ${response.code()}")
//                emptyList()
//            }
//        } catch (e: Exception) {
//            Log.d("RemoteDataSource", "Всё не ок: ${e.message}")
//            emptyList()
//        }
//    }
//
//    private fun handleJSONResponse(responseBody: String?): List<Categories> {
//        try {
//            Log.d("handleJSONResponse", "JSON response body: $responseBody")
//            val moshi = Moshi.Builder().build()
//            val jsonAdapter = moshi.adapter(CategoriesResponse::class.java)
//            val categoriesResponse = jsonAdapter.fromJson(responseBody)
//            val categories : List<Categories> = categoriesResponse?.categories ?: emptyList()
//            Log.d("handleJSONResponse", "всё ок: $categories")
//            return categories
//        } catch (e: Exception) {
//            Log.e("handleJSONResponse", "Error parsing JSON response", e)
//            // Ошибка при разборе JSON-объекта
//        }
//        return emptyList()
//    }
//
//    fun getDishes(): List<Dish> {
//        return try {
//            val response = apiService.getDishes().execute()
//            if (response.isSuccessful) {
//                response.body() ?: emptyList()
//            } else {
//                emptyList()
//            }
//        } catch (e: Exception) {
//            emptyList()
//        }
//    }
//}