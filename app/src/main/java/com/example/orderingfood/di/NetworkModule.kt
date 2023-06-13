package com.example.orderingfood.di

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        // Создание перехватчика для логирования
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(
                "NetworkModule",
                message
            )
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY //
        }


        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}

