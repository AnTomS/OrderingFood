package com.example.orderingfood.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orderingfood.domain.dto.Dish


@Entity
class DishesEntity(
    @PrimaryKey(autoGenerate = true)
    val description: String = "",
    val id: Int = 0,
    val imageUrl: String? = "",
    val name: String = "",
    val price: Int = 0,
    val tegs: String = "",
    val weight: Int = 0

) {
    fun toDto() = Dish(description, id, imageUrl, name, price, tegs, weight)


    companion object {
        fun fromDto(dto: Dish) = DishesEntity(
            dto.description,
            dto.id,
            dto.imageUrl,
            dto.name,
            dto.price,
            dto.tegs,
            dto.weight
        )
    }
}

fun List<DishesEntity>.toDto(): List<Dish> = map(DishesEntity::toDto)
fun List<Dish>.toEntity(): List<DishesEntity> = map(DishesEntity::fromDto)