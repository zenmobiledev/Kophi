package com.mobbelldev.kophi.data.source.remote.datasource

import com.mobbelldev.kophi.data.source.remote.api.CoffeeService
import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrderSnapResponse
import com.mobbelldev.kophi.data.source.remote.model.response.TransactionResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class CoffeeRemoteDataSourceImpl @Inject constructor(private val coffeeService: CoffeeService) :
    CoffeeRemoteDataSource {
    override suspend fun getCoffeeList(usId: Int): Response<CoffeeResponse> {
        val response = coffeeService.getCoffeeList(
            userId = usId
        )
        return try {
            response
        } catch (e: Exception) {
            val responseCode = response.code()
            val errorBody = response.errorBody()
            Response.error(
                responseCode,
                errorBody ?: (e.message
                    ?: "Unknown Error").toResponseBody("application/json".toMediaTypeOrNull())
            )
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

    override suspend fun continueWithGoogle(
        continueWithGoogle: ContinueWithGoogleRequest,
    ): Response<AuthenticationResponse> {
        try {
            val response = coffeeService.continueWithGoogle(
                continueWithGoogle = continueWithGoogle
            )
            return when {
                response.isSuccessful -> response
                else -> throw Exception(response.code().toString())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun createOrderSnap(
        userId: Int,
        orderRequest: OrderRequest,
    ): Response<OrderSnapResponse> {
        val response = coffeeService.createOrderSnap(
            orderRequest = orderRequest,
            userId = userId
        )
        return try {
            response
        } catch (e: Exception) {
            val responseCode = response.code()
            val errorBody = response.errorBody()
            Response.error(
                responseCode,
                errorBody ?: (e.message
                    ?: "Unknown Error").toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
    }
}