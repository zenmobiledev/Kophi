package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class InsertCoffeeCartUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(coffee: CoffeeCart) = repository.insertCoffeeCart(
        coffee = coffee
    )
}