package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class IncrementQuantityUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(cartId: String) = repository.incrementCoffeeCartQuantity(
        cartId = cartId
    )
}