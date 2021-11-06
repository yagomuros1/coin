package com.yago.coin.data.api.response

import com.google.gson.annotations.SerializedName

data class UserTokenResponse(

    @SerializedName("token")
    val token: String,

    @SerializedName("refreshToken")
    val refreshToken: String

)