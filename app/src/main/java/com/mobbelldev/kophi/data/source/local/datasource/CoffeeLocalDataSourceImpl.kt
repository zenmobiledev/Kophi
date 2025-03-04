package com.mobbelldev.kophi.data.source.local.datasource

import com.mobbelldev.kophi.data.source.local.dao.CoffeeCartDao
import com.mobbelldev.kophi.data.source.local.entity.CoffeeCartEntity
import com.mobbelldev.kophi.data.source.local.preference.PreferenceDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoffeeLocalDataSourceImpl @Inject constructor(
    private val preference: PreferenceDataStore,
    private val coffeeCartDao: CoffeeCartDao,
) :
    CoffeeLocalDataSource {
    override suspend fun setOnboarding(isOnboarding: Boolean) {
        preference.setOnboarding(isOnboarding)
    }

    override suspend fun getOnboarding(): Boolean {
        return preference.getOnboarding()
    }

    override suspend fun saveTokenToDatabase(token: String) {
        preference.saveToken(
            token = token
        )
    }

    override suspend fun getToken(): String {
        return preference.getToken()
    }

    override suspend fun saveUserId(userId: Int) {
        preference.saveUserId(userId)
    }

    override suspend fun getUserId(): Int {
        return preference.getUserId()
    }

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

    override suspend fun setAuthenticationUser(isAuthenticated: Boolean) {
        preference.setAuthenticationUser(isAuthenticated)
    }

    override suspend fun getAuthenticationUser(): Boolean {
        return preference.getAuthenticationUser()
    }

    override suspend fun setEmail(email: String) {
        preference.setEmail(email)
    }

    override suspend fun getEmail(): String {
        return preference.getEmail()
    }

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        preference.setDarkMode(isDarkMode)
    }

    override fun getDarkMode(): Flow<Boolean> {
        return preference.getDarkMode()
    }

    override suspend fun setLanguage(language: String) {
        preference.setLanguage(language)
    }

    override fun getLanguage(): Flow<String> {
        return preference.getLanguage()
    }

    override suspend fun logout() {
        preference.logout()
    }

}