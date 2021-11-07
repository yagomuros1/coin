package com.yago.coin.data.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class TransactionSkuData(

    val id: Long,

    val sku: String,

    val count: Int

)