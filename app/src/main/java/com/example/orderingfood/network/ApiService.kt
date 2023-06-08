package com.example.orderingfood.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {


    val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    val call = apiService.getDishes()
    call.enqueue(
    object : Callback<List<Dish>> {
        override fun onResponse(call: Call<List<Dish>>, response: Response<List<Dish>>) {
            if (response.isSuccessful) {
                val dishes = response.body()
                // Обработка полученных данных
            } else {
                // Обработка ошибки
            }
        }

        override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
            // Обработка ошибки
        }
    })

    object ApiServiceFactory {
        private const val BASE_URL = "https://run.mocky.io/"

        fun createApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}