package com.yago.coin.data.db.dao

import androidx.room.*
import com.yago.coin.data.db.entity.Trade
import com.yago.coin.data.db.entity.TransactionSkuData

@Dao
interface TradeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: Trade)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(data: Trade)

    @Transaction
    fun save(data: Trade) {
        insert(data)
        update(data)
    }

    @Query("SELECT * FROM Trade WHERE id = :id")
    fun findById(id: String): Trade

    @Query("SELECT * FROM Trade")
    fun getAllData(): List<Trade>

    @Query("SELECT t.id as id, t.sku as sku, count(*) as count FROM TRADE as t GROUP BY t.sku")
    fun getAllDistinctSkus(): List<TransactionSkuData>

    @Query("SELECT * FROM Trade WHERE Trade.sku == :sku")
    fun getTradesBySku(sku: String): List<Trade>

    @Query("DELETE FROM Trade")
    fun removeAllData()

}