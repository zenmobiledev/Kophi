package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.entity.CartCoffeeEntity
import com.example.kophi.data.source.local.entity.CoffeeEntity

interface CoffeeLocalDataSource {
    suspend fun insertCoffee(coffee: CartCoffeeEntity)

    suspend fun getCoffeeList(): List<CoffeeEntity>
}