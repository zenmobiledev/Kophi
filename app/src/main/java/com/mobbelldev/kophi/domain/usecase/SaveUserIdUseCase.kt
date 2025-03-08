package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(userId: Int) = repository.saveUserId(userId = userId)
}