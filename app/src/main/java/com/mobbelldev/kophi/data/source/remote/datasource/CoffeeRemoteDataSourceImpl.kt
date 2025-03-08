package com.mobbelldev.kophi.data.source.remote.datasource

import com.mobbelldev.kophi.data.source.remote.api.CoffeeService
import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CancelOrderResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrderSnapResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class CoffeeRemoteDataSourceImpl @Inject constructor(private val coffeeService: CoffeeService) :
    CoffeeRemoteDataSource {
    override suspend fun getCoffeeList(token: String, userId: Int): Response<CoffeeResponse> {
        return try {
            coffeeService.getCoffeeList(userId = userId, token = token)
        } catch (e: SocketTimeoutException) {
            createErrorResponse(408, "Request Timeout, silakan coba lagi.")
        } catch (e: IOException) {
            createErrorResponse(503, "Koneksi internet bermasalah.")
        } catch (e: Exception) {
            createErrorResponse(500, e.message ?: "Terjadi kesalahan.")
        }
    }

    override suspend fun getOrders(token: String, userId: Int): Response<OrdersResponse> {
        return try {
            coffeeService.getOrders(userId = userId, token = token)
        } catch (e: SocketTimeoutException) {
            createErrorResponse(408, "Request Timeout, silakan coba lagi.")
        } catch (e: IOException) {
            createErrorResponse(503, "Koneksi internet bermasalah.")
        } catch (e: Exception) {
            createErrorResponse(500, e.message ?: "Terjadi kesalahan.")
        }
    }

    override suspend fun cancelOrder(
        userId: Int,
        token: String,
        transactionId: String,
    ): Response<CancelOrderResponse> {
        return try {
            coffeeService.cancelOrder(userId = userId, transactionId = transactionId, token = token)
        } catch (e: SocketTimeoutException) {
            createErrorResponse(408, "Request Timeout, silakan coba lagi.")
        } catch (e: IOException) {
            createErrorResponse(503, "Koneksi internet bermasalah.")
        } catch (e: Exception) {
            createErrorResponse(500, e.message ?: "Terjadi kesalahan.")
        }
    }

    override suspend fun continueWithGoogle(
        continueWithGoogle: ContinueWithGoogleRequest,
    ): Response<AuthenticationResponse> {
        return try {
            coffeeService.continueWithGoogle(continueWithGoogle)
        } catch (e: SocketTimeoutException) {
            createErrorResponse(408, "Request Timeout, silakan coba lagi.")
        } catch (e: IOException) {
            createErrorResponse(503, "Koneksi internet bermasalah.")
        } catch (e: Exception) {
            createErrorResponse(500, e.message ?: "Terjadi kesalahan.")
        }
    }

    override suspend fun createOrderSnap(
        token: String,
        userId: Int,
        orderRequest: OrderRequest,
    ): Response<OrderSnapResponse> {
        return try {
            coffeeService.createOrderSnap(
                orderRequest = orderRequest,
                userId = userId,
                token = token
            )
        } catch (e: SocketTimeoutException) {
            createErrorResponse(408, "Request Timeout, silakan coba lagi.")
        } catch (e: IOException) {
            createErrorResponse(503, "Koneksi internet bermasalah.")
        } catch (e: Exception) {
            createErrorResponse(500, e.message ?: "Terjadi kesalahan.")
        }
    }

    private fun <T> createErrorResponse(code: Int, message: String): Response<T> {
        return Response.error(
            code,
            message.toResponseBody("application/json".toMediaTypeOrNull())
        )
    }
}