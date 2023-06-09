package com.example.data.di

import com.example.data.network.NetworkModule
import com.example.data.repository.Repository
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun getRepository(): Repository

}