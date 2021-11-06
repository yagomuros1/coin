package com.yago.coin.data.api.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("description")
    val description: String

)