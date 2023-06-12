package com.example.data.network

import com.example.domain.model.Categories
import com.example.domain.model.CategoriesResponse
import com.example.domain.model.Dish
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    fun getCategories(): Call<CategoriesResponse>

    @GET("v3/c7a508f2-a904-498a-8539-09d96785446e")
    fun getDishes(): Call<List<Dish>>
}