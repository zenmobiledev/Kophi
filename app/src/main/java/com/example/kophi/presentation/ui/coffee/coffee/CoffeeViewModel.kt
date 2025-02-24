package com.example.kophi.presentation.ui.coffee.coffee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kophi.domain.model.Coffee
import com.example.kophi.domain.usecase.CoffeeUseCase
import com.example.kophi.utils.ResultResponse
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
    private val _coffeeData = MutableStateFlow(
        Coffee(
            data = emptyList()
        )
    )
    val coffeeData: StateFlow<Coffee> = _coffeeData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage.asSharedFlow()

    init {
        getCoffeeList()
    }

    fun getCoffeeList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                coffeeUseCase().collect { result ->
                    when (result) {
                        is ResultResponse.Error -> {
                            _isLoading.value = false
                            _errorMessage.emit(result.message)
                        }

                        ResultResponse.Loading -> _isLoading.value = true
                        is ResultResponse.Success -> {
                            _isLoading.value = false
                            result.data?.let {
                                _coffeeData.value = Coffee(
                                    data = it.data
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}