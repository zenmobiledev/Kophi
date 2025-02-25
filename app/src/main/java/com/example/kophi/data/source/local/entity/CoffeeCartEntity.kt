package com.example.kophi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeCartEntity(
    @PrimaryKey val coffeeId: String,
    val id: Int,
    val image: String,
    val name: String,
    val temperature: String?,
    val milkOption: String?,
    val sweetness: String?,
    val price: Int,
)
