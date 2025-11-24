package com.example.soporte.features.tickets.domain.model

import java.time.LocalDateTime

data class Ticket(
    val id: Int,
    val nroTicket: String,
    val asunto: String,
    val estado: String,
    val prioridad: String,
    val solicitante: String,
    val fecha: LocalDateTime
)
