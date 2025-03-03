package com.mobbelldev.kophi.data.source.remote.model.response


import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("day")
        val day: Int,
        @SerializedName("roles")
        val roles: List<Role>,
        @SerializedName("token")
        val token: String,
        @SerializedName("us_active")
        val usActive: Boolean,
        @SerializedName("us_created_on")
        val usCreatedOn: UsCreatedOn,
        @SerializedName("us_email")
        val usEmail: String,
        @SerializedName("us_fullname")
        val usFullname: String,
        @SerializedName("us_id")
        val usId: Int,
        @SerializedName("us_phone_number")
        val usPhoneNumber: String,
        @SerializedName("us_updated_on")
        val usUpdatedOn: UsUpdatedOn,
        @SerializedName("us_username")
        val usUsername: String
    ) {
        data class Role(
            @SerializedName("rl_code")
            val rlCode: String
        )

        data class UsCreatedOn(
            @SerializedName("val")
            val valX: String
        )

        data class UsUpdatedOn(
            @SerializedName("val")
            val valX: String
        )
    }
}