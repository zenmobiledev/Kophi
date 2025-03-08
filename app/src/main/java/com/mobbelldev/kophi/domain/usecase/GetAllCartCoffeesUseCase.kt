package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class GetAllCartCoffeesUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(): List<CoffeeCart> = repository.getAllCartCoffees()
}