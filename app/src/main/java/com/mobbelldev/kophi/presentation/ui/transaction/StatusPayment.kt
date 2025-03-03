package com.mobbelldev.kophi.presentation.ui.transaction

enum class StatusPayment(val status: String) {
    PAID("Paid"),
    EXPIRED("Expired"),
    FAILED("Failed")
}