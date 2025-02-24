package com.example.kophi.data.source.remote.datasource

import com.example.kophi.data.source.remote.api.CoffeeService
import com.example.kophi.data.source.remote.model.CoffeeResponse
import retrofit2.Response
import javax.inject.Inject

class CoffeeRemoteDataSourceImpl @Inject constructor(private val coffeeService: CoffeeService) :
    CoffeeRemoteDataSource {
    override suspend fun getCoffeeList(): Response<CoffeeResponse> {
        try {
            val response = coffeeService.getCoffeeList()
            return when {
                response.isSuccessful -> response
                else -> throw Exception(response.code().toString())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}