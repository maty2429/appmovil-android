package com.example.soporte.features.auth.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    @Json(name = "rut") val rut: String
)

@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    @Json(name = "token") val token: String,
    @Json(name = "user") val user: UserDto
)

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "nombre_completo") val nombre: String,
    @Json(name = "rut") val rut: String,
    @Json(name = "correo") val correo: String
)
