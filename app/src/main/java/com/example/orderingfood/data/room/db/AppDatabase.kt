package com.example.orderingfood.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orderingfood.data.room.dao.OrderDao
import com.example.orderingfood.data.room.entity.CategoriesEntity
import com.example.orderingfood.data.room.entity.DishesEntity


@Database(entities = [DishesEntity::class, CategoriesEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
}