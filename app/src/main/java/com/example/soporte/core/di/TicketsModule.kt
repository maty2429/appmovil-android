package com.example.soporte.core.di

import com.example.soporte.features.tickets.data.repository.TicketRepositoryImpl
import com.example.soporte.features.tickets.domain.repository.TicketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TicketsModule {
    @Binds
    abstract fun bindTicketRepository(
        impl: TicketRepositoryImpl
    ): TicketRepository
}
