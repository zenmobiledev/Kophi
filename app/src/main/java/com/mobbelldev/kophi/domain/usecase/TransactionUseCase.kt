package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.CancelOrder
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(token: String, userId: Int): Flow<ResultResponse<Orders>> {
        return coffeeRepository.getOrders(
            userId = userId,
            token = token
        )
    }

    suspend operator fun invoke(
        userId: Int,
        token: String,
        transactionId: String,
    ): Flow<ResultResponse<CancelOrder>> {
        return coffeeRepository.cancelOrder(
            userId = userId,
            token = token,
            transactionId = transactionId
        )
    }

    suspend fun getUserId(): Int {
        return coffeeRepository.getUserId()
    }

    suspend fun getToken(): String {
        return coffeeRepository.getToken()
    }
}