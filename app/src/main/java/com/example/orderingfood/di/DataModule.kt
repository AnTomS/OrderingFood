package com.example.orderingfood.di

import com.example.orderingfood.data.network.ApiServiceInterface
import com.example.orderingfood.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryImpl(
         apiServiceInterface: ApiServiceInterface
    ): RepositoryImpl {
        return RepositoryImpl(apiServiceInterface)
    }
}