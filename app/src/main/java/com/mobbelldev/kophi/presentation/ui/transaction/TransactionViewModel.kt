package com.mobbelldev.kophi.presentation.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.model.Orders
import com.mobbelldev.kophi.domain.usecase.TransactionUseCase
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
class TransactionViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) :
    ViewModel() {

    private val _orders = MutableStateFlow(
        value = Orders(
            data = emptyList()
        )
    )
    val orders: StateFlow<Orders> = _orders.asStateFlow()

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

    private fun getOrders(token: String, userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                transactionUseCase(
                    userId = userId,
                    token = token
                ).collect { result ->
                    when (result) {
                        is ResultResponse.Error -> {
                            _isLoading.value = false
                            _errorMessage.emit(result.message)
                        }

                        is ResultResponse.Loading -> _isLoading.value = true
                        is ResultResponse.Success -> {
                            _isLoading.value = false
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

    private suspend fun getUserId(): Int {
        return transactionUseCase.getUserId()
    }

    private suspend fun getToken(): String {
        return transactionUseCase.getToken()
    }
}