package com.example.orderingfood.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


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

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://run.mocky.io/v3/")
            .client(httpClient)
            .build()

    }

}

