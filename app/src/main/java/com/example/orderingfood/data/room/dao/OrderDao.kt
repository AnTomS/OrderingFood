package com.example.orderingfood.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orderingfood.data.room.entity.CategoriesEntity
import com.example.orderingfood.data.room.entity.DishesEntity


@Dao
interface OrderDao {

    @Query("SELECT * FROM CategoriesEntity")
    suspend fun getCategories(): List<CategoriesEntity>

    @Query("SELECT * FROM DishesEntity")
    suspend fun getAllDishes(): List<DishesEntity>

    @Query("SELECT * FROM DishesEntity WHERE id = :dishId")
    fun getDishesById(dishId: Int): LiveData<DishesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishes(dishes: List<DishesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoriesEntity>)

    @Query("SELECT * FROM DishesEntity WHERE tegs = :tag")
    suspend fun detDishesByTag(tag: List<String>): List<DishesEntity>

}