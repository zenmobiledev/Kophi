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
    private val _coffeeData = MutableSharedFlow<Coffee>()
    val coffeeData: SharedFlow<Coffee> = _coffeeData

    private val _coffeeList = MutableSharedFlow<List<CoffeeCart>>()
    val coffeeList: SharedFlow<List<CoffeeCart>> = _coffeeList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    init {
        viewModelScope.launch {
            getCoffeeList(coffeeUseCase.getUsId())
        }
    }

    private fun getCoffeeList(usId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase(usId).collect { result ->
                    when (result) {
                        is ResultResponse.Error -> {
                            _isLoading.value = false
                            _errorMessage.emit(result.message)
                        }

                        is ResultResponse.Loading -> _isLoading.value = true
                        is ResultResponse.Success -> {
                            _isLoading.value = false
                            result.data?.let {
                                _coffeeData.emit(
                                    Coffee(
                                        data = it.data
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
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