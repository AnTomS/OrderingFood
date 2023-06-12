package com.example.orderingfood.di

import android.util.Log
import com.example.data.repository.RepositoryImpl
import com.example.orderingfood.MainActivity
import com.example.orderingfood.ui.categories.MainFragment
import com.example.orderingfood.ui.dishes.DishesFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment) {
        Log.d("AppComponent", "MainFragment")
    }

    fun inject(activity: MainActivity) {
        Log.d("AppComponent", "Injecting MainActivity")
    }

    fun inject(fragment: DishesFragment){
        Log.d("AppComponent", "DishesFragment")
    }

    fun provideRepositoryImpl(): RepositoryImpl
}
