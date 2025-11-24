package com.example.soporte.features.tickets.data.remote

import com.example.soporte.features.tickets.data.remote.dto.TicketResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface TicketApi {
    @GET("tickets")
    suspend fun getTickets(): Response<TicketResponseDto>
}
