package com.example.orderingfood.di

import com.example.domain.model.repository.Repository
import com.example.domain.model.usecase.GetCategoriesUseCase
import com.example.domain.model.usecase.GetDishesUseCase
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