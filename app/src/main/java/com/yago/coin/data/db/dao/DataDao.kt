package com.yago.coin.data.db.dao

import androidx.room.*
import com.yago.coin.data.db.entity.Data

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: Data)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(data: Data)

    @Transaction
    fun save(data: Data) {
        insert(data)
        update(data)
    }

    @Query("SELECT * FROM data WHERE id = :id")
    fun findByStoryId(id: String): Data

    @Query("SELECT * FROM data")
    fun getAllData(): List<Data>

}