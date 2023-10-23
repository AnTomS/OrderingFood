package com.example.orderingfood.data

import com.example.orderingfood.data.room.entity.CategoriesEntity
import com.example.orderingfood.data.room.entity.DishesEntity
import com.example.orderingfood.domain.dto.Categories
import com.example.orderingfood.domain.dto.Dish

open class DataMapper {


    @JvmName("mapCategoriesEntitiesToDto")
    fun List<CategoriesEntity>.toDto(): List<Categories> {
        return map { remoteCategory ->
            Categories(remoteCategory.id, remoteCategory.imageUrl, remoteCategory.name)
        }
    }

    @JvmName("mapDishesEntitiesToDto")
    fun List<DishesEntity>.toDto(): List<Dish> {
        return map { remoteDish ->
            Dish(
                remoteDish.description,
                remoteDish.id,
                remoteDish.imageUrl,
                remoteDish.name,
                remoteDish.price,
                remoteDish.tegs,
                remoteDish.weight,
            )
        }
    }
}