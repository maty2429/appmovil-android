package com.example.soporte.features.tickets.domain.repository

import com.example.soporte.core.network.NetworkResult
import com.example.soporte.features.tickets.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getTickets(): Flow<NetworkResult<List<Ticket>>>
    suspend fun refreshTickets()
}
