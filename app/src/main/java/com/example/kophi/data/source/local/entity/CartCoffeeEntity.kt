package com.example.kophi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartCoffeeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val temperature: String?,
    val milkOption: String?,
    val sweetness: String?,
    val price: Int,
)
