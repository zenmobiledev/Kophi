package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.CancelOrder
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CancelOrderUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(
        userId: Int,
        token: String,
        transactionId: String,
    ): Flow<ResultResponse<CancelOrder>> = repository.cancelOrder(
        userId = userId,
        token = token,
        transactionId = transactionId
    )
}