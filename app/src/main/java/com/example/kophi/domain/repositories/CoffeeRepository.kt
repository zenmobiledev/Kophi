package com.example.kophi.domain.repositories

import com.example.kophi.domain.model.Coffee
import com.example.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow

interface CoffeeRepository {
    suspend fun getCoffeeList(): Flow<ResultResponse<Coffee>>
}