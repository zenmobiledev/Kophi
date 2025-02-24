package com.example.kophi.utils

import java.text.NumberFormat
import java.util.Locale

object IDRCurrency {
    fun format(money: Int): String {
        val currency = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        return currency.format(money)
    }
}