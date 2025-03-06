package com.mobbelldev.kophi.domain.model

data class CancelOrder(
    val `data`: Data,
) {
    data class Data(
        val details: List<Detail>,
        val orId: Int,
        val orPaymentStatus: String,
        val orPlatformId: String,
        val orStatus: String,
        val orTotalPrice: Int,
        val orUpdatedBy: String,
        val orUpdatedOn: String,
    ) {
        data class Detail(
            val odProducts: List<OdProduct>,
        ) {
            data class OdProduct(
                val id: Int,
                val name: String,
                val price: Int,
                val quantity: Int,
            )
        }
    }
}
