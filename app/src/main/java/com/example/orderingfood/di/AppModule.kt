package com.example.orderingfood.di

import android.app.Application
import android.content.Context
import dagger.Provides
import javax.inject.Singleton

class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}