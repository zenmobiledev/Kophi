package com.mobbelldev.kophi.data.source.remote.datasource

import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CancelOrderResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrderSnapResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse
import retrofit2.Response

interface CoffeeRemoteDataSource {
    suspend fun getCoffeeList(token: String, userId: Int): Response<CoffeeResponse>

    suspend fun continueWithGoogle(continueWithGoogle: ContinueWithGoogleRequest): Response<AuthenticationResponse>

    suspend fun createOrderSnap(
        token: String,
        userId: Int,
        orderRequest: OrderRequest,
    ): Response<OrderSnapResponse>

    suspend fun getOrders(token: String, userId: Int): Response<OrdersResponse>

    suspend fun cancelOrder(
        userId: Int,
        token: String,
        transactionId: String,
    ): Response<CancelOrderResponse>
}