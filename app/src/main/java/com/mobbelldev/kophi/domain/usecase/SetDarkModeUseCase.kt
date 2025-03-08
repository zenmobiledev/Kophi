package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend operator fun invoke(isDarkMode: Boolean) = repository.setDarkMode(
        isDarkMode = isDarkMode
    )
}