package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.dao.CoffeeCartDao
import com.example.kophi.data.source.local.entity.CoffeeCartEntity
import javax.inject.Inject

class CoffeeLocalDataSourceImpl @Inject constructor(private val coffeeCartDao: CoffeeCartDao) :
    CoffeeLocalDataSource {
    override suspend fun insertCoffeeCart(coffee: CoffeeCartEntity) {

        val coffeeCart = coffeeCartDao.getCart(
            coffee.coffeeId,
            coffee.temperature ?: "",
            coffee.milkOption ?: "",
            coffee.sweetness ?: ""
        )

        if (coffeeCart != null) {
            val newQuantity = coffee.quantity + coffeeCart.quantity
            coffeeCartDao.updateCoffeeCartQuantityAndSubtotal(coffeeCart.id, newQuantity)
        } else {
            coffeeCartDao.insertCoffeeCart(coffee)
        }

    }

    override suspend fun getAllCartCoffees(): List<CoffeeCartEntity> {
        return coffeeCartDao.getAllCartCoffees()
    }

    override suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int) {
        coffeeCartDao.updateCoffeeCartQuantityAndSubtotal(cartId, newQuantity)
    }

    override suspend fun incrementCoffeeCartQuantity(cartId: String) {
        coffeeCartDao.incrementCoffeeCartQuantity(cartId)
    }

    override suspend fun decrementCoffeeCartQuantity(cartId: String) {
        coffeeCartDao.decrementCoffeeCartQuantity(cartId)
    }

    override suspend fun deleteCoffeeCart(cartId: String) {
        coffeeCartDao.deleteCoffeeCart(cartId)
    }


}