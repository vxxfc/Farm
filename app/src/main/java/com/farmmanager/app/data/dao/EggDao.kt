package com.farmmanager.app.data.dao

import androidx.room.*
import com.farmmanager.app.data.entity.EggRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface EggDao {
    @Query("SELECT * FROM egg_records ORDER BY date DESC")
    fun getAll(): Flow<List<EggRecord>>

    @Query("SELECT * FROM egg_records WHERE flockId = :flockId ORDER BY date DESC")
    fun getByFlock(flockId: Long): Flow<List<EggRecord>>

    @Insert
    suspend fun insert(record: EggRecord): Long

    @Update
    suspend fun update(record: EggRecord)

    @Delete
    suspend fun delete(record: EggRecord)

    @Query("SELECT COALESCE(SUM(quantityCollected), 0) FROM egg_records WHERE date BETWEEN :start AND :end")
    fun getTotalCollectedBetween(start: Long, end: Long): Flow<Int>
}
