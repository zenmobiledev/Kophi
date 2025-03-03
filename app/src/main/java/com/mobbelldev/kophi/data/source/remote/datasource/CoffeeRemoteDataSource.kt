package com.mobbelldev.kophi.data.source.remote.datasource

import com.mobbelldev.kophi.data.source.remote.model.request.ContinueWithGoogleRequest
import com.mobbelldev.kophi.data.source.remote.model.response.AuthenticationResponse
import com.mobbelldev.kophi.data.source.remote.model.response.CoffeeResponse
import com.mobbelldev.kophi.data.source.remote.model.response.TransactionResponse
import retrofit2.Response

interface CoffeeRemoteDataSource {
    suspend fun getCoffeeList(usId: Int): Response<CoffeeResponse>

    suspend fun getTransaction(): Response<TransactionResponse>

    suspend fun continueWithGoogle(continueWithGoogle: ContinueWithGoogleRequest): Response<AuthenticationResponse>
}