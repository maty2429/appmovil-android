package com.example.soporte.features.auth.domain.repository

import com.example.soporte.core.network.NetworkResult
import com.example.soporte.features.auth.domain.model.User

interface AuthRepository {
    suspend fun login(rut: String): NetworkResult<User>
    fun logout()
    fun isUserLoggedIn(): Boolean
}
