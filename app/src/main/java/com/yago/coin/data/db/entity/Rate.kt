package com.yago.coin.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["from","to"])
data class Rate(

    val from: String,

    val to: String,

    val rate: String

)