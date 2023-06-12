package com.example.orderingfood.di

import com.example.data.repository.RepositoryImpl
import com.example.orderingfood.ui.categories.MainFragment
import com.example.orderingfood.ui.dishes.DishesFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment)

    fun inject(fragment: DishesFragment)

    fun provideRepositoryImpl(): RepositoryImpl
}
