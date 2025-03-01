package com.example.kophi.domain.usecase

import com.example.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend fun logout() {
        return repository.logout()
    }
}