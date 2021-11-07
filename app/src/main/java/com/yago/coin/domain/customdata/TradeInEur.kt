package com.yago.coin.domain.customdata

import androidx.room.Entity
import androidx.room.PrimaryKey


data class TradeInEur(

    val sku: String?,

    val amount: String?,

    val eurAmount: String,

    val currency: String?

)