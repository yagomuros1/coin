package com.yago.coin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trade(

    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    val sku: String?,

    val amount: Double?,

    val currency: String?

)