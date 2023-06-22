package com.example.orderingfood.di

import com.example.data.local.LocalDataSource
import com.example.data.local.RemoteDataSource
import com.example.data.network.ApiServiceInterface
import com.example.data.repository.RepositoryImpl
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