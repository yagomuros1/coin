package com.yago.coin.data.db.dao

import androidx.room.*
import com.yago.coin.data.db.entity.Rate

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: Rate)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(data: Rate)

    @Transaction
    fun save(data: Rate) {
        insert(data)
        update(data)
    }

    @Query("SELECT * FROM Rate")
    fun getAllData(): List<Rate>

    @Query("DELETE FROM Rate")
    fun removeAllData()

}