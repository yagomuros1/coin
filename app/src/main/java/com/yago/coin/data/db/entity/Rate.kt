package com.yago.coin.data.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["from", "to"])
data class Rate(

    val from: String,

    val to: String,

    val rate: Double

)