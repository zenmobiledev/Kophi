package com.example.kophi.data.source.remote.datasource

import com.example.kophi.data.source.remote.model.CoffeeResponse
import retrofit2.Response

interface CoffeeRemoteDataSource {
    suspend fun getCoffeeList(): Response<CoffeeResponse>
}