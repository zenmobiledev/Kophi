package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.dao.CoffeeCartDao
import com.example.kophi.data.source.local.entity.CoffeeCartEntity
import javax.inject.Inject

class CoffeeLocalDataSourceImpl @Inject constructor(private val coffeeCartDao: CoffeeCartDao) :
    CoffeeLocalDataSource {
    override suspend fun insertCoffeeCart(coffee: CoffeeCartEntity) {
        coffeeCartDao.insertCoffeeCart(coffee)
    }

    override suspend fun getAllCartCoffees(): List<CoffeeCartEntity> {
        return coffeeCartDao.getAllCartCoffees()
    }
}