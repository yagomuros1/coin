package com.yago.coin.data.db.entity

import androidx.room.Entity

@Entity(
    primaryKeys = ["id"]
)

data class Data (

    val id : String,

    val description : String

)