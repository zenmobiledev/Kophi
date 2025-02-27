package com.example.kophi.domain.repositories

import com.example.kophi.domain.model.Coffee
import com.example.kophi.domain.model.CoffeeCart
import com.example.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    suspend fun getCoffeeList(): Flow<ResultResponse<Coffee>>

    suspend fun insertCoffeeCart(coffee: CoffeeCart)

    suspend fun getAllCartCoffees(): List<CoffeeCart>

    suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int)

    suspend fun incrementCoffeeCartQuantity(cartId: String)

    suspend fun decrementCoffeeCartQuantity(cartId: String)

    suspend fun deleteCoffeeCart(cartId: String)
}