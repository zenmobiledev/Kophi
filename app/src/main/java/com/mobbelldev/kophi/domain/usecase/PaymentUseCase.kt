package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class PaymentUseCase @Inject constructor(private val repository: CoffeeRepository)