package com.example.soporte.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.soporte.features.tickets.data.local.TicketDao
import com.example.soporte.features.tickets.data.local.entity.TicketEntity

@Database(entities = [TicketEntity::class], version = 1)
@TypeConverters(com.example.soporte.core.database.TypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}
