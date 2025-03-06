package com.mobbelldev.kophi.presentation.ui.coffee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.kophi.domain.model.Coffee
import com.mobbelldev.kophi.domain.model.CoffeeCart
import com.mobbelldev.kophi.domain.usecase.CoffeeUseCase
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
class CoffeeViewModel @Inject constructor(private val coffeeUseCase: CoffeeUseCase) : ViewModel() {
    private val _coffeeData = MutableStateFlow<Coffee?>(null)
    val coffeeData: StateFlow<Coffee?> = _coffeeData.asStateFlow()

    private val _coffeeList = MutableStateFlow<List<CoffeeCart>>(emptyList())
    val coffeeList: StateFlow<List<CoffeeCart>> = _coffeeList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    init {
        viewModelScope.launch {
            val token = getToken()
            val userId = getUserId()

            if (_coffeeData.value == null) {
                getCoffeeList(token, userId)
            }
            getAllCartCoffees()
        }
    }

    private fun getCoffeeList(token: String, userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase(
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
                            result.data?.let { _coffeeData.value = it }
                        }
                    }
                }
            }
        }
    }

    suspend fun getUserId(): Int {
        return coffeeUseCase.getUserId()
    }

    suspend fun getToken(): String {
        return coffeeUseCase.getToken()
    }

    fun insertCoffeeCart(coffee: CoffeeCart) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase.insertCoffeeCart(coffee)
            }
        }
    }

    fun getAllCartCoffees() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _coffeeList.emit(coffeeUseCase.getAllCartCoffees())
            }
        }
    }
}