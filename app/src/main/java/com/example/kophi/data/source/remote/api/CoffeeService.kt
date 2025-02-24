package com.example.kophi.data.source.remote.api

import com.example.kophi.data.source.remote.model.CoffeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoffeeService {
    @GET("coffee-list")
    suspend fun getCoffeeList(): Response<CoffeeResponse>
}