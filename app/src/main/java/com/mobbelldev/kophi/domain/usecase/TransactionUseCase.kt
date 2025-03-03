package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.Transaction
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(): Flow<ResultResponse<Transaction>> {
        return coffeeRepository.getTransactionList()
    }
}