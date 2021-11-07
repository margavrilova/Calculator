package com.example.calculator.data.db.history

import androidx.room.*
import com.example.calculator.data.db.typeConverters.LocalDateTimeCoverter

@Dao
@TypeConverters(LocalDateTimeCoverter::class)
interface HistoryItemDao {

    @Insert
    suspend fun insert(historyItemEntity: HistoryItemEntity)

    @Delete
    suspend fun delete(historyItemEntity: HistoryItemEntity)

    @Delete
    suspend fun delete(historyItemEntities: List<HistoryItemEntity>)

    @Query("SElECT * FROM history_item_entity")
    suspend fun getAll(): List<HistoryItemEntity>
}