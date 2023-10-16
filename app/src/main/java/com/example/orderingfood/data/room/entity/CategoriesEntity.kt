package com.example.orderingfood.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orderingfood.domain.dto.Categories


@Entity
class CategoriesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val imageUrl: String = "",
    val name: String = ""
) {
    fun toDto() = Categories(id, imageUrl, name)

    companion object {
        fun fromDto(dto: Categories) = CategoriesEntity(dto.id, dto.imageUrl, dto.name)
    }
}

fun List<CategoriesEntity>.toDto(): List<Categories> = map(CategoriesEntity::toDto)
fun List<Categories>.toEntity(): List<CategoriesEntity> = map(CategoriesEntity::fromDto)
