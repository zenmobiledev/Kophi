package com.mobbelldev.kophi.presentation.ui.coffee.payment

import androidx.lifecycle.ViewModel
import com.mobbelldev.kophi.domain.usecase.PaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val paymentUseCase: PaymentUseCase) :
    ViewModel()