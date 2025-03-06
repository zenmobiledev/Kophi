package com.mobbelldev.kophi.data.source.remote.api

import com.mobbelldev.kophi.BuildConfig
import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.request.OrderRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CancelOrderResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrderSnapResponse
import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CoffeeService {
    @GET("api/kophi/menu/items")
    suspend fun getCoffeeList(
        @Header("x-token") token: String,
        @Header("x-user-id") userId: Int,
        @Header("x-secret-app") secretApp: String = BuildConfig.X_SECRET_APP,
    ): Response<CoffeeResponse>


    @POST("auth/continue-with-google")
    suspend fun continueWithGoogle(
        @Body continueWithGoogle: ContinueWithGoogleRequest,
    ): Response<AuthenticationResponse>

    @POST("api/kophi/order/snap")
    suspend fun createOrderSnap(
        @Header("x-token") token: String,
        @Header("x-user-id") userId: Int,
        @Header("x-secret-app") secretApp: String = BuildConfig.X_SECRET_APP,
        @Body orderRequest: OrderRequest,
    ): Response<OrderSnapResponse>

    @GET("api/kophi/orders")
    suspend fun getOrders(
        @Header("x-token") token: String,
        @Header("x-user-id") userId: Int,
        @Header("x-secret-app") secretApp: String = BuildConfig.X_SECRET_APP,
    ): Response<OrdersResponse>

    @POST("api/kophi/order/cancel/{transaction_id}")
    suspend fun cancelOrder(
        @Header("x-user-id") userId: Int,
        @Header("x-token") token: String,
        @Header("x-secret-app") secretApp: String = BuildConfig.X_SECRET_APP,
        @Path("transaction_id") transactionId: String,
    ): Response<CancelOrderResponse>

}