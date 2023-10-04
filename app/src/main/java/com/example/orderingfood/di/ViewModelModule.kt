package com.example.orderingfood.di

import androidx.lifecycle.ViewModelProvider
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.presentation.ui.viewmodels.CategoriesViewModel
import com.example.orderingfood.presentation.ui.viewmodels.DishesDetailsViewModel
import com.example.orderingfood.presentation.ui.viewmodels.DishesViewModel
import com.example.orderingfood.presentation.ui.viewmodels.ViewModelFactory
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

    @Provides
    fun provideDishesDetailsViewModel(repository: RepositoryImpl): DishesDetailsViewModel {
        return DishesDetailsViewModel(repository)
    }
}