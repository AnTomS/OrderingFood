package com.example.orderingfood.di

import com.example.data.network.ApiServiceInterface
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

class DataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }
}