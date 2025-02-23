package com.example.kophi.data.repository

import com.example.kophi.data.mapper.Mapper
import com.example.kophi.data.source.remote.datasource.CoffeeRemoteDataSource
import com.example.kophi.domain.model.Coffee
import com.example.kophi.domain.repositories.CoffeeRepository
import com.example.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
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
}