package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class DecrementQuantityUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(cartId: String) = repository.decrementCoffeeCartQuantity(
        cartId = cartId
    )
}