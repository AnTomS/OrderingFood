package com.example.orderingfood.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.orderingfood.data.room.dao.DishesDao
import com.example.orderingfood.data.room.entity.DishesEntity


@Database(entities = [DishesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dishesDao(): DishesDao
}