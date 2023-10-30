package com.example.orderingfood.di

import android.app.Application
import androidx.room.Room
import com.example.orderingfood.data.network.ApiServiceInterface
import com.example.orderingfood.data.repository.RepositoryImpl
import com.example.orderingfood.data.repository.datasource.LocalDataSource
import com.example.orderingfood.data.repository.datasource.RemoveDataSource
import com.example.orderingfood.data.room.dao.OrderDao
import com.example.orderingfood.data.room.db.AppDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule(application: Application) {

    private val appDatabase: AppDatabase = Room.databaseBuilder(
        application, AppDatabase::class.java, "app-database"
    ).build()


    @Provides
    @Singleton
    fun provideDishesDao(): OrderDao {
        return appDatabase.orderDao()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryImpl(
        apiServiceInterface: ApiServiceInterface
    ): RepositoryImpl {
        val orderDao: OrderDao = appDatabase.orderDao()
        return RepositoryImpl(localDataSource = LocalDataSource(orderDao), remoteDataSource = RemoveDataSource(apiServiceInterface))
    }
}