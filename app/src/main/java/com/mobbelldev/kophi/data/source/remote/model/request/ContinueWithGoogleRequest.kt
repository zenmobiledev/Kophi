package com.mobbelldev.kophi.data.source.remote.model.request

data class ContinueWithGoogleRequest(
    val rememberMe: Boolean = true,
    val token: String,
)
