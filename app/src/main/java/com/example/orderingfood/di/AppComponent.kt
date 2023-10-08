package com.example.orderingfood.di

import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.orderingfood.MainActivity
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.presentation.ui.DishesDetailsDialogFragment
import com.example.orderingfood.presentation.ui.categories.MainFragment
import com.example.orderingfood.presentation.ui.dishes.DishesFragment
import com.example.orderingfood.presentation.ui.dishesdetails.DishesDetailsFragment
import com.example.orderingfood.presentation.ui.shop.CartFragment

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class, ViewModelModule::class, DomainModule::class])
interface AppComponent {
    fun inject(fragment: MainFragment) {
        Log.d("AppComponent", "Injecting MainFragment")
    }

    fun inject(activity: MainActivity) {
        Log.d("AppComponent", "Injecting MainActivity")
    }

    fun inject(fragment: DishesFragment) {
        Log.d("AppComponent", "Injecting DishesFragment")
    }

    fun inject(fragment: DishesDetailsDialogFragment) {
        Log.d("AppComponent", "Injecting DishDetailsDialogFragment")
    }

    fun inject(fragment: CartFragment){
        Log.d("AppComponent", "Injecting CartFragment")
    }

//    fun inject(fragment: DishesDetailsFragment) {
//        Log.d("AppComponent", "Injecting DishDetailsFragment")
//    }

    fun provideRepositoryImpl(): RepositoryImpl


}
