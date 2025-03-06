package com.mobbelldev.kophi.utils

fun String.capitalizeFirst(): String {
    return this.replaceFirstChar { it.uppercase() }
}