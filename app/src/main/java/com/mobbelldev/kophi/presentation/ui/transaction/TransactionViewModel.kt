package com.mobbelldev.kophi.presentation.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.model.CancelOrder
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.domain.usecase.CancelOrderUseCase
import com.mobbelldev.kophi.domain.usecase.GetOrdersUseCase
import com.mobbelldev.kophi.domain.usecase.GetTokenUseCase
import com.mobbelldev.kophi.domain.usecase.GetUserIdUseCase
import com.mobbelldev.kophi.utils.ResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val cancelOrderUseCase: CancelOrderUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _orders = MutableStateFlow<Orders?>(
        null
    )
    val orders: StateFlow<Orders?> = _orders.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

    init {
        viewModelScope.launch {
            getOrders(
                userId = getUserId(),
                token = getToken(),
            )
        }
    }

    fun getOrders(token: String, userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getOrdersUseCase(
                    userId = userId,
                    token = token
                ).collect { result ->
                    when (result) {
                        is ResultResponse.Error -> {
                            _errorMessage.emit(result.message)
                        }

                        is ResultResponse.Loading -> {}
                        is ResultResponse.Success -> {
                            result.data.let {
                                _orders.value = Orders(
                                    data = it?.data ?: emptyList()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun cancelOrder(userId: Int, token: String, transactionId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cancelOrderUseCase(
                    userId = userId,
                    token = token,
                    transactionId = transactionId
                ).collect { result ->
                    when (result) {
                        is ResultResponse.Error -> {
                            _isLoading.value = false
                            _errorMessage.emit(result.message)
                        }

                        is ResultResponse.Loading -> _isLoading.value = true
                        is ResultResponse.Success -> {
                            result.data?.let {
                                CancelOrder(
                                    data = it.data
                                )
                            }
                            getOrders(
                                token = token,
                                userId = userId
                            )
                            _isLoading.value = false
                        }
                    }
                }
            }
        }
    }

    suspend fun getUserId(): Int = getUserIdUseCase()

    suspend fun getToken(): String = getTokenUseCase()
}