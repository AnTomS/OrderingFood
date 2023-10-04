package com.example.orderingfood.di

import com.example.orderingfood.domain.repository.Repository
import com.example.orderingfood.domain.usecase.GetCategoriesUseCase
import com.example.orderingfood.domain.usecase.GetDishesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetCategoriesUseCase(repository: Repository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDishesUseCase(repository: Repository): GetDishesUseCase {
        return GetDishesUseCase(repository)
    }

}