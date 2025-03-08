package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class SetEmailUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(email: String) = repository.setEmail(
        email = email
    )
}