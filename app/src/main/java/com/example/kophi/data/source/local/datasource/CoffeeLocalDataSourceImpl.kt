package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.dao.CoffeeDao
import com.example.kophi.data.source.local.entity.CoffeeCartEntity
import javax.inject.Inject

class CoffeeLocalDataSourceImpl @Inject constructor(private val coffeeDao: CoffeeDao) :
    CoffeeLocalDataSource {
    override suspend fun insertCoffeeCart(coffee: CoffeeCartEntity) {
        coffeeDao.insertCoffeeCart(coffee)
    }

    override suspend fun getAllCoffeeProducts(): List<CoffeeCartEntity> {
        return coffeeDao.getAllCoffeeProducts()
    }


}