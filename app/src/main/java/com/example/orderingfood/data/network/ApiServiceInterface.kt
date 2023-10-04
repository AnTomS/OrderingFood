package com.example.orderingfood.data.network

import com.example.orderingfood.domain.dto.Cat
import com.example.orderingfood.domain.dto.Dishes
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceInterface {
    /*
        https://run.mocky.io/v3/c7a508f2-a904-498a-8539-09d96785446e
        API for dishes


        https://run.mocky.io/v3/058729bd-1402-4578-88de-265481fd7d54
            API for categories
     */

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): Response<Cat>

    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): Response<Dishes>
}