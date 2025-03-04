package com.mobbelldev.kophi.data.repository

import android.util.Log
import com.mobbelldev.kophi.data.mapper.Mapper
import com.mobbelldev.kophi.data.source.local.datasource.CoffeeLocalDataSource
import com.mobbelldev.kophi.data.source.remote.datasource.CoffeeRemoteDataSource
import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.domain.model.Authentication
import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.OrderSnap
import com.mobbelldev.kophi.domain.model.Transaction
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.CancellationException
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeLocalDataSource: CoffeeLocalDataSource,
    private val coffeeRemoteDataSource: CoffeeRemoteDataSource,
    private val mapper: Mapper,
) : CoffeeRepository {
    override suspend fun setOnboarding(isOnboarding: Boolean) {
        coffeeLocalDataSource.setOnboarding(isOnboarding)
    }

    override suspend fun getOnboarding(): Boolean {
        return coffeeLocalDataSource.getOnboarding()
    }

    override suspend fun continueWithGoogle(continueWithGoogle: ContinueWithGoogleRequest): Flow<ResultResponse<Authentication>> {
        return flow {
            emit(ResultResponse.Loading)
            val response = coffeeRemoteDataSource.continueWithGoogle(continueWithGoogle)
            try {
                if (response.isSuccessful) {
                    val authenticationResponse = response.body()?.let {
                        mapper.mapResponseToDomain(it)
                    }
                    emit(ResultResponse.Success(authenticationResponse))
                }
            } catch (e: Exception) {
                emit(ResultResponse.Error(e.message.toString()))
            }
        }
    }

    override suspend fun saveTokenToDatabase(token: String) {
        coffeeLocalDataSource.saveTokenToDatabase(token)
    }

    override suspend fun getToken(): String {
        return coffeeLocalDataSource.getToken()
    }

    override suspend fun getCoffeeList(usId: Int): Flow<ResultResponse<Coffee>> {
        return flow {
            emit(ResultResponse.Loading)
            val response = coffeeRemoteDataSource.getCoffeeList(
                usId = usId
            )
            try {
                if (response.isSuccessful) {
                    val coffeeResponse = response.body()?.let {
                        mapper.mapResponseToDomain(it)
                    }
                    emit(ResultResponse.Success(coffeeResponse))
                }
            } catch (e: CancellationException) {
                Log.d("Repository", "Flow cancelled")
                throw e
            } catch (e: Exception) {
                emit(ResultResponse.Error(e.message.toString()))
            }
        }
    }

    override suspend fun saveUsId(usId: Int) {
        coffeeLocalDataSource.saveUsId(usId)
    }

    override suspend fun getUsId(): Int {
        return coffeeLocalDataSource.getUsId()
    }

    override suspend fun insertCoffeeCart(coffee: CoffeeCart) {
        coffeeLocalDataSource.insertCoffeeCart(mapper.mapDomainToEntities(coffee))
    }

    override suspend fun getAllCartCoffees(): List<CoffeeCart> {
        return mapper.mapEntityToDomain(coffeeLocalDataSource.getAllCartCoffees())
    }

    override suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int) {
        coffeeLocalDataSource.updateCoffeeCartQuantityAndSubtotal(cartId, newQuantity)
    }

    override suspend fun incrementCoffeeCartQuantity(cartId: String) {
        coffeeLocalDataSource.incrementCoffeeCartQuantity(cartId)
    }

    override suspend fun decrementCoffeeCartQuantity(cartId: String) {
        coffeeLocalDataSource.decrementCoffeeCartQuantity(cartId)
    }

    override suspend fun deleteCoffeeCart(cartId: String) {
        coffeeLocalDataSource.deleteCoffeeCart(cartId)
    }

    override suspend fun createOrderSnap(
        userId: Int,
        orderRequest: OrderRequest,
    ): Flow<ResultResponse<OrderSnap>> {
        return flow {
            emit(ResultResponse.Loading)
            val response = coffeeRemoteDataSource.createOrderSnap(
                orderRequest = orderRequest,
                userId = userId
            )
            try {
                if (response.isSuccessful) {
                    val domainToResponse = response.body()?.let {
                        mapper.mapResponseToDomain(it)
                    }
                    emit(ResultResponse.Success(domainToResponse))
                }

            } catch (e: Exception) {
                emit(ResultResponse.Error(e.message.toString()))
            }
        }
    }

    override suspend fun getTransactionList(): Flow<ResultResponse<Transaction>> {
        return flow {
            emit(ResultResponse.Loading)
            val response = coffeeRemoteDataSource.getTransaction()
            try {
                if (response.isSuccessful) {
                    val transactionResponse = response.body()?.let {
                        mapper.mapResponseToDomain(it)
                    }
                    emit(ResultResponse.Success(transactionResponse))
                }
            } catch (e: Exception) {
                emit(ResultResponse.Error(e.message.toString()))
            }
        }
    }

    override suspend fun setAuthenticateUser(isAuthenticated: Boolean) {
        coffeeLocalDataSource.setAuthenticationUser(isAuthenticated)
    }

    override suspend fun getAuthenticateUser(): Boolean {
        return coffeeLocalDataSource.getAuthenticationUser()
    }

    override suspend fun setEmail(email: String) {
        coffeeLocalDataSource.setEmail(email)
    }

    override suspend fun getEmail(): String {
        return coffeeLocalDataSource.getEmail()
    }

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        coffeeLocalDataSource.setDarkMode(isDarkMode)
    }

    override fun getDarkMode(): Flow<Boolean> {
        return coffeeLocalDataSource.getDarkMode()
    }

    override suspend fun setLanguage(language: String) {
        coffeeLocalDataSource.setLanguage(language)
    }

    override fun getLanguage(): Flow<String> {
        return coffeeLocalDataSource.getLanguage()
    }

    override suspend fun logout() {
        coffeeLocalDataSource.logout()
    }
}