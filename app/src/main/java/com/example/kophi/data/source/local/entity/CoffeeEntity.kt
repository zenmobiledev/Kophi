package com.example.kophi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val image: String,
    val title: String,
    val description: String,
    val temperature: List<String?>?,
    val milkOption: List<String?>?,
    val sweetness: List<String?>?,
    val price: Int,
)