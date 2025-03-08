package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class GetEmailUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(): String = repository.getEmail()
}