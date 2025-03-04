package com.mobbelldev.kophi.domain.model

data class ContinueWithGoogle(
    val rememberMe: Boolean = true,
    val token: String,
)
