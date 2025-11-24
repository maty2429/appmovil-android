package com.example.soporte.features.tickets.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.soporte.features.tickets.domain.model.Ticket
import java.time.LocalDateTime

@Entity(tableName = "tickets")
data class TicketEntity(
    @PrimaryKey val id: Int,
    val nroTicket: String,
    val asunto: String,
    val estado: String,
    val prioridad: String,
    val solicitante: String,
    val fechaCreacion: String,
    val isSynced: Boolean = true
)

fun TicketEntity.toDomain() = Ticket(
    id = id,
    nroTicket = nroTicket,
    asunto = asunto,
    estado = estado,
    prioridad = prioridad,
    solicitante = solicitante,
    fecha = LocalDateTime.parse(fechaCreacion)
)
