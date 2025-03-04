package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.OrderSnap
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import com.mobbelldev.kophi.utils.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(
        userId: Int,
        email: String,
        price: Int,
        items: List<OrderRequest.Item>,
    ): Flow<ResultResponse<OrderSnap>> {
        return repository.createOrderSnap(
            orderRequest = OrderRequest(
                amount = price,
                callbacks = OrderRequest.Callbacks(
                    error = toString(),
                    finish = toString()
                ),
                email = email,
                items = items,
                promoCodes = emptyList()
            ),
            userId = userId
        )
    }

    suspend fun getUsId(): Int {
        return repository.getUsId()
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

    suspend fun getEmail(): String {
        return repository.getEmail()
    }
}