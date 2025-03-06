package com.mobbelldev.kophi.presentation.ui.transaction.adapter

interface OnItemClickListener {
    fun onClickPay(orderTokenId: String)
    fun onCancelClick(transactionId: String)
}