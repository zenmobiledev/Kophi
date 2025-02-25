package com.example.kophi.domain.usecase

import com.example.kophi.domain.model.Coffee
import com.example.kophi.domain.model.CoffeeCart
import com.example.kophi.domain.repositories.CoffeeRepository
import com.example.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoffeeUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(): Flow<ResultResponse<Coffee>> {
        return coffeeRepository.getCoffeeList()
    }

    suspend fun insertCoffeeCart(coffee: CoffeeCart) {
        coffeeRepository.insertCoffeeCart(coffee)
    }

    suspend fun getAllCoffeeProducts(): List<CoffeeCart> {
        return coffeeRepository.getAllCoffeeProducts()
    }
}