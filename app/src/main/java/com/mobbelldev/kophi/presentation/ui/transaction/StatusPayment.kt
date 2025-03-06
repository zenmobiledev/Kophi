package com.mobbelldev.kophi.presentation.ui.transaction

enum class StatusPayment(val status: String) {
    PAID("paid"),
    EXPIRED("expired"),
    PENDING("pending"),
    CANCELLED("cancelled")
}