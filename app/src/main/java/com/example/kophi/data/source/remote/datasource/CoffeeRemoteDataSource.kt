package com.example.kophi.data.source.remote.datasource

import com.example.kophi.data.source.remote.model.CoffeeResponse
import com.example.kophi.data.source.remote.model.TransactionResponse
import retrofit2.Response

interface CoffeeRemoteDataSource {
    suspend fun getCoffeeList(): Response<CoffeeResponse>

    suspend fun getTransaction(): Response<TransactionResponse>
}