package com.example.soporte.features.tickets.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soporte.features.tickets.data.local.entity.TicketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Query("SELECT * FROM tickets ORDER BY id DESC")
    fun getAllTickets(): Flow<List<TicketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tickets: List<TicketEntity>)

    @Query("DELETE FROM tickets")
    suspend fun clearAll()
}
