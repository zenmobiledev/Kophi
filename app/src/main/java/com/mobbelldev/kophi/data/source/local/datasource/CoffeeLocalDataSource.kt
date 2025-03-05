package com.mobbelldev.kophi.data.source.local.datasource

import com.mobbelldev.kophi.data.source.local.entity.CoffeeCartEntity
import kotlinx.coroutines.flow.Flow

interface CoffeeLocalDataSource {
    suspend fun setOnboarding(isOnboarding: Boolean)

    suspend fun getOnboarding(): Boolean

    suspend fun saveTokenToDatabase(token: String)

    suspend fun getToken(): String

    suspend fun saveUserId(userId: Int)

    suspend fun getUserId(): Int

    suspend fun insertCoffeeCart(coffee: CoffeeCartEntity)

    suspend fun getAllCartCoffees(): List<CoffeeCartEntity>

    suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int)

    suspend fun incrementCoffeeCartQuantity(cartId: String)

    suspend fun decrementCoffeeCartQuantity(cartId: String)

    suspend fun deleteCoffeeCart(cartId: String)

    suspend fun deleteAllOrders(orders: CoffeeCartEntity)

    suspend fun setAuthenticationUser(isAuthenticated: Boolean)

    suspend fun getAuthenticationUser(): Boolean

    suspend fun setEmail(email: String)

    suspend fun getEmail(): String

    suspend fun setDarkMode(isDarkMode: Boolean)

    fun getDarkMode(): Flow<Boolean>

    suspend fun setLanguage(language: String)

    fun getLanguage(): Flow<String>

    suspend fun logout()
}