package com.example.kophi.domain.model

data class CartCoffee(
    val id: Int,
    val title: String,
    val temperature: String?,
    val milkOption: String?,
    val sweetness: String?,
    val price: Int,
)
