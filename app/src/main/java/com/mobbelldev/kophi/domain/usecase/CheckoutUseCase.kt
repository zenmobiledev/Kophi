package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.Order
import com.mobbelldev.kophi.domain.model.OrderSnap
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(
        token: String,
        userId: Int,
        email: String,
        price: Int,
        items: List<Order.Item>,
    ): Flow<ResultResponse<OrderSnap>> {
        return repository.createOrderSnap(
            orderRequest = Order(
                amount = price,
                callbacks = Order.Callbacks(
                    error = toString(),
                    finish = toString()
                ),
                email = email,
                items = items,
                promoCodes = emptyList()
            ),
            userId = userId,
            token = token
        )
    }
}