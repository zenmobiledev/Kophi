package com.mobbelldev.kophi.data.source.remote.datasource

import android.util.Log
import com.mobbelldev.kophi.data.source.remote.api.CoffeeService
import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.TransactionResponse
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class CoffeeRemoteDataSourceImpl @Inject constructor(private val coffeeService: CoffeeService) :
    CoffeeRemoteDataSource {
    override suspend fun getCoffeeList(usId: Int): Response<CoffeeResponse> {
        try {
            val response = coffeeService.getCoffeeList(
                userId = usId
            )
            return when {
                response.isSuccessful -> response
                else -> throw Exception(response.code().toString())
            }
        } catch (e: CancellationException) {
            withContext(NonCancellable) {
                Log.d("CoffeeRemoteDataSourceImpl", "Operation was cancelled")
            }
            throw e
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun getTransaction(): Response<TransactionResponse> {
        try {
            val response = coffeeService.getTransaction()
            return when {
                response.isSuccessful -> response
                else -> throw Exception(response.code().toString())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun continueWithGoogle(continueWithGoogle: ContinueWithGoogleRequest): Response<AuthenticationResponse> {
        try {
            val response = coffeeService.continueWithGoogle(continueWithGoogle)
            return when {
                response.isSuccessful -> response
                else -> throw Exception(response.code().toString())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}