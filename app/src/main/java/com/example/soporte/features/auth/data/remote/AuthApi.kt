package com.example.soporte.features.auth.data.remote

import com.example.soporte.features.auth.data.remote.dto.LoginRequestDto
import com.example.soporte.features.auth.data.remote.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>
}
