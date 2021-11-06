package com.yago.coin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yago.coin.data.db.dao.DataDao
import com.yago.coin.data.db.entity.Data

@Database(
    entities = [
        Data::class
    ],
    version = 1,
    exportSchema = false
)


abstract class CoinDb : RoomDatabase() {

    abstract fun dataDao(): DataDao

}