package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(token: String) = repository.saveTokenToDatabase(token)
}