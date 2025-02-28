package com.example.kophi.data.repository

import com.example.kophi.data.mapper.Mapper
import com.example.kophi.data.source.local.datasource.CoffeeLocalDataSource
import com.example.kophi.data.source.remote.datasource.CoffeeRemoteDataSource
import com.example.kophi.domain.model.Coffee
import com.example.kophi.domain.model.CoffeeCart
import com.example.kophi.domain.model.Transaction
import com.example.kophi.domain.repositories.CoffeeRepository
import com.example.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val coffeeLocalDataSource: CoffeeLocalDataSource,
    private val coffeeRemoteDataSource: CoffeeRemoteDataSource,
    private val mapper: Mapper,
) : CoffeeRepository {
    override suspend fun getCoffeeList(): Flow<ResultResponse<Coffee>> {
        return flow {
            emit(ResultResponse.Loading)
            val response = coffeeRemoteDataSource.getCoffeeList()
            try {
                if (response.isSuccessful) {
                    val coffeeResponse = response.body()?.let {
                        mapper.mapResponseToDomain(it)
                    }
                    emit(ResultResponse.Success(coffeeResponse))
                }
            } catch (e: Exception) {
                emit(ResultResponse.Error(e.message.toString()))
            }
        }
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
}