package com.example.data.network

import com.example.data.model.Categories
import com.example.data.model.Dish
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    fun getCategories(): Call<List<Categories>>

    @GET("v3/c7a508f2-a904-498a-8539-09d96785446e")
    fun getDishes(): Call<List<Dish>>
}