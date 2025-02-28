package com.example.kophi.domain.usecase

import com.example.kophi.domain.model.Transaction
import com.example.kophi.domain.repositories.CoffeeRepository
import com.example.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(): Flow<ResultResponse<Transaction>> {
        return coffeeRepository.getTransactionList()
    }
}