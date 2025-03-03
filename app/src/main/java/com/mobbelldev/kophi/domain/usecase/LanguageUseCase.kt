package com.mobbelldev.kophi.domain.usecase

import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguageUseCase @Inject constructor(private val repository: CoffeeRepository) {
    suspend fun setLanguage(language: String) = repository.setLanguage(language)

    fun getLanguage(): Flow<String> = repository.getLanguage()
}