package com.example.kophi.utils

import java.text.NumberFormat
import java.util.Locale

object IDRCurrency {
    fun format(money: Int): String {
        val currency = NumberFormat.getCurrencyInstance(Locale("in", "ID")).apply {
            maximumFractionDigits = 0
        }
        return currency.format(money)
    }
}