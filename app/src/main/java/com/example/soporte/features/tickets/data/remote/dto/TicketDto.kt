package com.example.soporte.features.tickets.data.remote.dto

import com.example.soporte.features.tickets.data.local.entity.TicketEntity

data class TicketDto(
    val id: Int,
    val nroTicket: String,
    val asunto: String,
    val estado: String,
    val prioridad: String,
    val solicitante: String,
    val fechaCreacion: String,
    val isSynced: Boolean = true
)

fun TicketDto.toEntity() = TicketEntity(
    id = id,
    nroTicket = nroTicket,
    asunto = asunto,
    estado = estado,
    prioridad = prioridad,
    solicitante = solicitante,
    fechaCreacion = fechaCreacion,
    isSynced = isSynced
)
