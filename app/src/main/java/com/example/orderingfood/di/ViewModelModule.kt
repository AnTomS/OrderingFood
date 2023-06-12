package com.example.orderingfood.di

import androidx.lifecycle.ViewModelProvider
import com.example.data.local.LocalDataSource
import com.example.data.local.RemoteDataSource
import com.example.data.repository.RepositoryImpl
import com.example.orderingfood.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(repository: RepositoryImpl): ViewModelProvider.Factory {
        return ViewModelFactory(repository)
    }
    @Provides
    @Singleton
    fun provideRepositoryImpl(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): RepositoryImpl {
        return RepositoryImpl(localDataSource, remoteDataSource)
    }
}