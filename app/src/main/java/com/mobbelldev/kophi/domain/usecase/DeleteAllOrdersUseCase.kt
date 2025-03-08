package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class DeleteAllOrdersUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(orders: CoffeeCart) = repository.deleteAllOrders(
        orders = orders
    )
}