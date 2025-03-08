package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(token: String, userId: Int): Flow<ResultResponse<Orders>> =
        repository.getOrders(
            token = token,
            userId = userId
        )
}