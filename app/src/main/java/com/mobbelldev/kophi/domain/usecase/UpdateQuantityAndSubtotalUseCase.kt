package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class UpdateQuantityAndSubtotalUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(cartId: String, newQuantity: Int) =
        repository.updateCoffeeCartQuantityAndSubtotal(
            cartId = cartId,
            newQuantity = newQuantity
        )
}