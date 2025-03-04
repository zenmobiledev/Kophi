package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoffeeUseCase @Inject constructor(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(usId: Int): Flow<ResultResponse<Coffee>> {
        return coffeeRepository.getCoffeeList(
            usId = usId
        )
    }

    suspend fun getUsId(): Int {
        return coffeeRepository.getUsId()
    }

    suspend fun insertCoffeeCart(coffee: CoffeeCart) {
        coffeeRepository.insertCoffeeCart(coffee)
    }

    suspend fun getAllCartCoffees(): List<CoffeeCart> {
        return coffeeRepository.getAllCartCoffees()
    }
}