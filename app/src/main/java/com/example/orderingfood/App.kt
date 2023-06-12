package com.example.orderingfood

import android.app.Application
import com.example.orderingfood.di.AppComponent
import com.example.orderingfood.di.AppModule
import com.example.orderingfood.di.DaggerAppComponent
import com.example.orderingfood.di.DataModule
import com.example.orderingfood.di.NetworkModule
import com.example.orderingfood.di.ViewModelModule


class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        // Инициализируйте компонент Dagger при создании приложения
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .viewModelModule(ViewModelModule())
            .networkModule(NetworkModule())
            .build()
    }
}