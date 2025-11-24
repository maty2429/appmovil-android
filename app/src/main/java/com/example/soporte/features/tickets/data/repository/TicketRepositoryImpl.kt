package com.example.soporte.features.tickets.data.repository

import com.example.soporte.core.network.NetworkResult
import com.example.soporte.features.tickets.data.local.TicketDao
import com.example.soporte.features.tickets.data.local.entity.toDomain
import com.example.soporte.features.tickets.data.remote.TicketApi
import com.example.soporte.features.tickets.data.remote.dto.toEntity
import com.example.soporte.features.tickets.domain.model.Ticket
import com.example.soporte.features.tickets.domain.repository.TicketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val api: TicketApi,
    private val dao: TicketDao
) : TicketRepository {

    override fun getTickets(): Flow<NetworkResult<List<Ticket>>> = flow {
        emit(NetworkResult.Loading(true))

        dao.getAllTickets().collect { entities ->
            emit(NetworkResult.Success(entities.map { it.toDomain() }))

            if (entities.isEmpty()) {
                refreshTickets()
            }
        }
    }

    override suspend fun refreshTickets() {
        try {
            val response = api.getTickets()
            if (response.isSuccessful) {
                val dtos = response.body()?.data.orEmpty()
                val entities = dtos.map { it.toEntity() }
                dao.insertAll(entities)
            }
        } catch (e: Exception) {
            // En offline no hacemos nada, la UI sigue mostrando caché.
        }
    }
}
