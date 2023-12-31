package com.example.orderingfood

import android.app.Application
import android.util.Log
import com.example.orderingfood.di.AppComponent
import com.example.orderingfood.di.AppModule
import com.example.orderingfood.di.DaggerAppComponent
import com.example.orderingfood.di.DataModule
import com.example.orderingfood.di.DomainModule
import com.example.orderingfood.di.NetworkModule
import com.example.orderingfood.di.ViewModelModule


class App : Application() {
    lateinit var appComponent : AppComponent

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule(this))
            .viewModelModule(ViewModelModule())
            .networkModule(NetworkModule())
            .build()

        Log.d("App", "AppComponent initialized: $appComponent")
    }
}