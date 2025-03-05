package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.model.CoffeeCart
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

    suspend fun getToken(): String {
        return repository.getToken()
    }

    suspend fun getUserId(): Int {
        return repository.getUserId()
    }

    suspend fun getAllCartCoffees(): List<CoffeeCart> {
        return repository.getAllCartCoffees()
    }

    suspend fun updateQuantityAndSubtotal(cartId: String, newQuantity: Int) {
        repository.updateCoffeeCartQuantityAndSubtotal(cartId, newQuantity)
    }

    suspend fun incrementQuantity(cartId: String) {
        repository.incrementCoffeeCartQuantity(cartId)
    }

    suspend fun decrementQuantity(cartId: String) {
        repository.decrementCoffeeCartQuantity(cartId)
    }

    suspend fun deleteCoffeeCart(cartId: String) {
        repository.deleteCoffeeCart(cartId)
    }

    suspend fun deleteAllOrders(orders: CoffeeCart) {
        return repository.deleteAllOrders(
            orders = orders
        )
    }

    suspend fun getEmail(): String {
        return repository.getEmail()
    }
}