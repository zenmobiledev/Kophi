package com.mobbelldev.kophi.presentation.ui.coffee.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.model.Order
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

    private val _urlSnap = MutableStateFlow<String>("")
    val urlSnap: StateFlow<String> = _urlSnap

    fun createOrderSnap(
        token: String,
        userId: Int,
        email: String,
        price: Int,
        items: MutableList<Order.Item>,
    ) {
        viewModelScope.launch {
            checkoutUseCase(
                email = email,
                price = price,
                items = items,
                userId = userId,
                token = token,
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

                            _urlSnap.value =
                                it.data.transaction.redirectUrl
                        }
                    }
                }
            }
        }
    }

    suspend fun getUserId(): Int {
        return checkoutUseCase.getUserId()
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

    fun deleteAllOrders(orders: CoffeeCart) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                checkoutUseCase.deleteAllOrders(orders)
//                refreshCart()
            }
        }
    }

    suspend fun getEmail(): String {
        return checkoutUseCase.getEmail()
    }

    suspend fun getToken(): String {
        return checkoutUseCase.getToken()
    }

    private suspend fun refreshCart() {
        _coffeeList.value = checkoutUseCase.getAllCartCoffees()
    }
}