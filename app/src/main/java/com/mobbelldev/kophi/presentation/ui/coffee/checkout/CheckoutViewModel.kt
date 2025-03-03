package com.mobbelldev.kophi.presentation.ui.coffee.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.OrderSnap
import com.mobbelldev.kophi.domain.usecase.CheckoutUseCase
import com.mobbelldev.kophi.utils.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val checkoutUseCase: CheckoutUseCase) :
    ViewModel() {

    private val _coffeeList = MutableStateFlow<List<CoffeeCart>>(emptyList())
    val coffeeList: StateFlow<List<CoffeeCart>> = _coffeeList.asStateFlow()

    private val _quantity = MutableStateFlow(0)
    val quantity: StateFlow<Int> = _quantity.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    fun createOrderSnap(
        userId: Int,
        email: String,
        price: Int,
        items: MutableList<OrderRequest.Item>,
    ) {
        viewModelScope.launch {
            checkoutUseCase(
                email = email,
                price = price,
                items = items,
                userId = userId,
            ).collect { result ->
                when (result) {
                    is ResultResponse.Error -> {
                        _isLoading.value = false
                        _errorMessage.emit(result.message)
                    }

                    is ResultResponse.Loading -> _isLoading.value = true
                    is ResultResponse.Success -> {
                        _isLoading.value = false
                        result.data?.let {
                            OrderSnap(
                                data = it.data
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun getUsId(): Int {
        return checkoutUseCase.getUsId()
    }

    fun getAllCartCoffees() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _coffeeList.value = checkoutUseCase.getAllCartCoffees()
            }
        }
    }

    fun incrementQuantity(cartId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                checkoutUseCase.incrementQuantity(cartId)
                refreshCart()
            }
        }
    }

    fun decrementQuantity(cartId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                checkoutUseCase.decrementQuantity(cartId)
                refreshCart()
            }
        }
    }

    fun updateQuantityAndSubtotal(cartId: String, newQuantity: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                checkoutUseCase.updateQuantityAndSubtotal(cartId, newQuantity)
                refreshCart()
            }
        }
    }

    fun deleteCoffeeCart(cartId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                checkoutUseCase.deleteCoffeeCart(cartId)
                refreshCart()
            }
        }
    }

    suspend fun getEmail(): String {
        return checkoutUseCase.getEmail()
    }

    private suspend fun refreshCart() {
        _coffeeList.value = checkoutUseCase.getAllCartCoffees()
    }
}