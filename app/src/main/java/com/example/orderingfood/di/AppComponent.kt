package com.example.orderingfood.di

import com.example.data.repository.Repository
import com.example.data.repository.RepositoryImpl
import com.example.orderingfood.ui.categories.MainFragment
import com.example.orderingfood.ui.dishes.DishesFragment
import com.example.orderingfood.viewmodels.DishesViewModel
import dagger.Component
import dagger.Module
import javax.inject.Singleton



@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)

    fun inject(fragment: DishesFragment)

}