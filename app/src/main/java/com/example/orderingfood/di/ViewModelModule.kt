package com.example.orderingfood.di

import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.RepositoryImpl
import com.example.orderingfood.viewmodels.CategoriesViewModel
import com.example.orderingfood.viewmodels.DishesViewModel
import com.example.orderingfood.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(repository: RepositoryImpl): ViewModelProvider.Factory {
        return ViewModelFactory(repository)
    }

    @Provides
    fun provideCategoriesViewModel(repository: RepositoryImpl): CategoriesViewModel {
        return CategoriesViewModel(repository)
    }

    @Provides
    fun provideDishesViewModel(repository: RepositoryImpl): DishesViewModel {
        return DishesViewModel(repository)
    }
}