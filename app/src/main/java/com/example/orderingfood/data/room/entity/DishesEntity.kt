package com.example.orderingfood.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.orderingfood.data.room.db.Converters
import com.example.orderingfood.domain.dto.Dish


@Entity
class DishesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String = "",
    val imageUrl: String? = "",
    val name: String = "",
    val price: Int = 0,
    @TypeConverters(Converters::class)
    val tegs: List<String> = emptyList(),
    val weight: Int = 0

) {
    fun toDto() = Dish(description, id, imageUrl, name, price, tegs, weight)


    companion object {
        fun fromDto(dto: Dish) = DishesEntity(
            dto.id,
            dto.description,
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

