package com.mobbelldev.kophi.domain.model

import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse.Data
import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse.Data.Detail
import com.mobbelldev.kophi.data.source.remote.model.response.OrdersResponse.Data.Detail.OdProduct

data class Orders(
    val `data`: List<Data>,
) {
    data class Data(
        val orId: Int,
        val orCreatedOn: String,
        val orTotalPrice: Int,
        val orStatus: String,
        val orTokenId: String,
        val orPlatformId: String,
        val details: List<Detail>,
    ) {
        data class Detail(
            val odProducts: List<OdProduct>,
        ) {
            data class OdProduct(
                val id: Int,
                val imageUrl: ImageUrl,
                val name: String,
                val price: Int,
                val quantity: Int,
            ) {
                data class ImageUrl(
                    val pdImageUrl: String,
                )
            }
        }
    }
}
