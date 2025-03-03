package com.mobbelldev.kophi.presentation.ui.coffee.checkout.adapter

interface AdapterCallback {
    fun onUpdateQuantity(cartId: String, newQuantity: Int)
    fun onIncrementQuantity(cartId: String)
    fun onDecrementQuantity(cartId: String)
    fun deleteItem(cartId: String)
}