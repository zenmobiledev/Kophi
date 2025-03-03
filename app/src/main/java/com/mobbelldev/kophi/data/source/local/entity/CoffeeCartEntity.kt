package com.mobbelldev.kophi.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoffeeCartEntity(
    val coffeeId: Int,
    @PrimaryKey val id: String,
    val image: String,
    val name: String,
    val temperature: String?,
    val milkOption: String?,
    val sweetness: String?,
    val price: Int,
    val quantity: Int,
    val subTotal: Int,
)