package com.mobbelldev.kophi.domain.repositories

import com.mobbelldev.kophi.domain.model.Authentication
import com.mobbelldev.kophi.domain.model.CancelOrder
import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.ContinueWithGoogle
import com.mobbelldev.kophi.domain.model.Order
import com.mobbelldev.kophi.domain.model.OrderSnap
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    suspend fun setOnboarding(isOnboarding: Boolean)

    suspend fun getOnboarding(): Boolean

    suspend fun continueWithGoogle(continueWithGoogle: ContinueWithGoogle): Flow<ResultResponse<Authentication>>

    suspend fun saveTokenToDatabase(token: String)

    suspend fun getToken(): String

    suspend fun getCoffeeList(token: String, userId: Int): Flow<ResultResponse<Coffee>>

    suspend fun saveUserId(userId: Int)

    suspend fun getUserId(): Int

    suspend fun insertCoffeeCart(coffee: CoffeeCart)

    suspend fun getAllCartCoffees(): List<CoffeeCart>

    suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int)

    suspend fun incrementCoffeeCartQuantity(cartId: String)

    suspend fun decrementCoffeeCartQuantity(cartId: String)

    suspend fun deleteCoffeeCart(cartId: String)

    suspend fun deleteAllOrders(orders: CoffeeCart)

    suspend fun createOrderSnap(
        token: String,
        userId: Int,
        orderRequest: Order,
    ): Flow<ResultResponse<OrderSnap>>

    suspend fun getOrders(token: String, userId: Int): Flow<ResultResponse<Orders>>

    suspend fun cancelOrder(
        userId: Int,
        token: String,
        transactionId: String,
    ): Flow<ResultResponse<CancelOrder>>

    suspend fun setAuthenticateUser(isAuthenticated: Boolean)

    suspend fun getAuthenticateUser(): Boolean

    suspend fun setEmail(email: String)

    suspend fun getEmail(): String

    suspend fun setDarkMode(isDarkMode: Boolean)

    fun getDarkMode(): Flow<Boolean>

    suspend fun setLanguage(language: String)

    fun getLanguage(): Flow<String>

    suspend fun logout()
}