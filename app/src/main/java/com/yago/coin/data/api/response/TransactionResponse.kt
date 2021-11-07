package com.yago.coin.data.api.response

import com.google.gson.annotations.SerializedName

data class TransactionResponse(

    @SerializedName("sku")
    val sku: String,

    @SerializedName("amount")
    val amount: String,

    @SerializedName("currency")
    val currency: String

)