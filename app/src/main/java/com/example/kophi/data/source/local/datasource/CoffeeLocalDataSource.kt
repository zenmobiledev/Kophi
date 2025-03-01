package com.example.kophi.data.source.local.datasource

import com.example.kophi.data.source.local.entity.CoffeeCartEntity

interface CoffeeLocalDataSource {
    suspend fun setOnboarding(isOnboarding: Boolean)

    suspend fun getOnboarding(): Boolean

    suspend fun insertCoffeeCart(coffee: CoffeeCartEntity)

    suspend fun getAllCartCoffees(): List<CoffeeCartEntity>

    suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int)

    suspend fun incrementCoffeeCartQuantity(cartId: String)

    suspend fun decrementCoffeeCartQuantity(cartId: String)

    suspend fun deleteCoffeeCart(cartId: String)

    suspend fun setAuthenticationUser(isAuthenticated: Boolean)

    suspend fun getAuthenticationUser(): Boolean

    suspend fun logout()
}