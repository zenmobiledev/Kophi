package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(userId: Int): Flow<ResultResponse<Orders>> {
        return coffeeRepository.getOrders(
            userId = userId
        )
    }

    suspend fun getUserId(): Int {
        return coffeeRepository.getUserId()
    }
}