package com.example.kophi.presentation.ui.coffee.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kophi.domain.model.CoffeeCart
import com.example.kophi.domain.usecase.CoffeeUseCase
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

    fun getAllCartCoffees() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _coffeeList.value = coffeeUseCase.getAllCartCoffees()
            }
        }
    }
}