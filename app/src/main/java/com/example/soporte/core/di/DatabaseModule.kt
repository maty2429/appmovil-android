package com.example.soporte.core.di

import android.content.Context
import androidx.room.Room
import com.example.soporte.core.database.AppDatabase
import com.example.soporte.features.tickets.data.local.TicketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "soporte_hospital_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTicketDao(db: AppDatabase): TicketDao = db.ticketDao()
}
