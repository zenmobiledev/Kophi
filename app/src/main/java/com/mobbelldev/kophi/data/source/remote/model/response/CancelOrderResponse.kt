package com.mobbelldev.kophi.data.source.remote.model.response


import com.google.gson.annotations.SerializedName

data class CancelOrderResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("details")
        val details: List<Detail>,
        @SerializedName("or_id")
        val orId: Int,
        @SerializedName("or_payment_status")
        val orPaymentStatus: String,
        @SerializedName("or_platform_id")
        val orPlatformId: String,
        @SerializedName("or_status")
        val orStatus: String,
        @SerializedName("or_total_price")
        val orTotalPrice: Int,
        @SerializedName("or_updated_by")
        val orUpdatedBy: String,
        @SerializedName("or_updated_on")
        val orUpdatedOn: String
    ) {
        data class Detail(
            @SerializedName("od_products")
            val odProducts: List<OdProduct>
        ) {
            data class OdProduct(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("price")
                val price: Int,
                @SerializedName("quantity")
                val quantity: Int
            )
        }
    }
}