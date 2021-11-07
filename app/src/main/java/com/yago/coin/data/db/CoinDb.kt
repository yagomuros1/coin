package com.yago.coin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yago.coin.data.db.dao.RateDao
import com.yago.coin.data.db.dao.TradeDao
import com.yago.coin.data.db.entity.Rate
import com.yago.coin.data.db.entity.Trade

@Database(
    entities = [
        Rate::class,
        Trade::class
    ],
    version = 1,
    exportSchema = false
)


abstract class CoinDb : RoomDatabase() {

    abstract fun rateDao(): RateDao

    abstract fun tradeDao(): TradeDao

}