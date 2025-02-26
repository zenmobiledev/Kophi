package com.example.kophi.domain.model

data class CoffeeCart(
    val coffeeId: String,
    val id: Int,
    val image: String,
    val name: String,
    val temperature: String?,
    val milkOption: String?,
    val sweetness: String?,
    val price: Int,
    val quantity: Int,
    val subTotal: Int,
)
