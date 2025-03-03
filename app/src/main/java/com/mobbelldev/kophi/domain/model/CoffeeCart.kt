package com.mobbelldev.kophi.domain.model

data class CoffeeCart(
    val coffeeId: Int,
    val id: String,
    val image: String,
    val name: String,
    val temperature: String?,
    val milkOption: String?,
    val sweetness: String?,
    val price: Int,
    val quantity: Int,
    val subTotal: Int,
)
