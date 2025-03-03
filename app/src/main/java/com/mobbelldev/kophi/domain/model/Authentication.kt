package com.mobbelldev.kophi.domain.model

data class Authentication(
    val `data`: Data,
) {
    data class Data(
        val usId: Int,
        val token: String,
    )
}
