package com.mobbelldev.kophi.presentation.ui.transaction

import androidx.lifecycle.ViewModel
import com.mobbelldev.kophi.domain.model.Transaction
import com.mobbelldev.kophi.domain.usecase.TransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val transactionUseCase: TransactionUseCase) :
    ViewModel() {
    private val _transactionList = MutableStateFlow(
        value = Transaction(
            data = emptyList()
        )
    )
    val transactionList: StateFlow<Transaction> = _transactionList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage

//    init {
//        getTransactionList()
//    }

//    private fun getTransactionList(userId: Int) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                transactionUseCase(
//                    userId = userId
//                ).collect { result ->
//                    when (result) {
//                        is ResultResponse.Error -> {
//                            _isLoading.value = false
//                            _errorMessage.emit(result.message)
//                        }
//
//                        is ResultResponse.Loading -> _isLoading.value = true
//                        is ResultResponse.Success -> {
//                            _isLoading.value = false
//                            result.data?.let {
//                                _transactionList.value = Transaction(
//                                    data = it.data
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}