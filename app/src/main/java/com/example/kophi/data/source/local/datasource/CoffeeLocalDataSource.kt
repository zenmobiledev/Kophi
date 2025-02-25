package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.entity.CoffeeCartEntity

interface CoffeeLocalDataSource {
    suspend fun insertCoffeeCart(coffee: CoffeeCartEntity)

    suspend fun getAllCoffeeProducts(): List<CoffeeCartEntity>
}