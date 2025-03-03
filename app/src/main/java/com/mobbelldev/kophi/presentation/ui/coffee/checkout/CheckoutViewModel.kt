package com.mobbelldev.kophi.presentation.ui.coffee.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.usecase.CoffeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val coffeeUseCase: CoffeeUseCase) :
    ViewModel() {

    private val _coffeeList = MutableStateFlow<List<CoffeeCart>>(emptyList())
    val coffeeList: StateFlow<List<CoffeeCart>> = _coffeeList.asStateFlow()

    private val _quantity = MutableStateFlow(0)
    val quantity: StateFlow<Int> = _quantity.asStateFlow()

    fun getAllCartCoffees() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _coffeeList.value = coffeeUseCase.getAllCartCoffees()
            }
        }
    }

    fun incrementQuantity(cartId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase.incrementQuantity(cartId)
                refreshCart()
            }
        }
    }

    fun decrementQuantity(cartId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase.decrementQuantity(cartId)
                refreshCart()
            }
        }
    }

    fun updateQuantityAndSubtotal(cartId: String, newQuantity: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase.updateQuantityAndSubtotal(cartId, newQuantity)
                refreshCart()
            }
        }
    }

    fun deleteCoffeeCart(cartId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase.deleteCoffeeCart(cartId)
                refreshCart()
            }
        }
    }

    private suspend fun refreshCart() {
        _coffeeList.value = coffeeUseCase.getAllCartCoffees()
    }
}