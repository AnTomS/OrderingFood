package com.example.orderingfood.data.repository.datasource

import com.example.orderingfood.data.room.dao.OrderDao
import com.example.orderingfood.data.room.entity.CategoriesEntity
import com.example.orderingfood.data.room.entity.DishesEntity

class LocalDataSource(private val orderDao: OrderDao) {

    suspend fun getCategories() = orderDao.getCategories()

    suspend fun insertDishesToDb(dishes: List<DishesEntity>) = orderDao.insertDishes(dishes)

    suspend fun insertCategoriesToBd(categories: List<CategoriesEntity>) =
        orderDao.insertCategories(categories)

    suspend fun detDishesByTag(tag: List<String>) = orderDao.detDishesByTag(tag)

    suspend fun getAllDishes() = orderDao.getAllDishes()
     fun getDishesById(dishId: Int) = orderDao.getDishesById(dishId)

}