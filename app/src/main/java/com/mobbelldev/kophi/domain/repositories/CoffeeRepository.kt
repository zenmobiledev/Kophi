package com.mobbelldev.kophi.domain.repositories

import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.domain.model.Authentication
import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.Transaction
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    suspend fun setOnboarding(isOnboarding: Boolean)

    suspend fun getOnboarding(): Boolean

    suspend fun continueWithGoogle(continueWithGoogle: ContinueWithGoogleRequest): Flow<ResultResponse<Authentication>>

    suspend fun saveTokenToDatabase(token: String)

    suspend fun getToken(): String

    suspend fun getCoffeeList(usId: Int): Flow<ResultResponse<Coffee>>

    suspend fun saveUsId(usId: Int)

    suspend fun getUsId(): Int

    suspend fun insertCoffeeCart(coffee: CoffeeCart)

    suspend fun getAllCartCoffees(): List<CoffeeCart>

    suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int)

    suspend fun incrementCoffeeCartQuantity(cartId: String)

    suspend fun decrementCoffeeCartQuantity(cartId: String)

    suspend fun deleteCoffeeCart(cartId: String)

    suspend fun getTransactionList(): Flow<ResultResponse<Transaction>>

    suspend fun setAuthenticateUser(isAuthenticated: Boolean)

    suspend fun getAuthenticateUser(): Boolean

    suspend fun setDarkMode(isDarkMode: Boolean)

    fun getDarkMode(): Flow<Boolean>

    suspend fun setLanguage(language: String)

    fun getLanguage(): Flow<String>

    suspend fun logout()
}