package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.dao.CoffeeDao
import com.example.kophi.data.source.local.entity.CartCoffeeEntity
import com.example.kophi.data.source.local.entity.CoffeeEntity
import javax.inject.Inject

class CoffeeLocalDataSourceImpl @Inject constructor(private val coffeeDao: CoffeeDao) :
    CoffeeLocalDataSource {
    override suspend fun insertCoffee(coffee: CartCoffeeEntity) {
        coffeeDao.insertCoffee(coffee)
    }

    override suspend fun getCoffeeList(): List<CoffeeEntity> {
        return emptyList()
    }
}