package com.example.soporte.features.auth.data.repository

import com.example.soporte.core.network.NetworkResult
import com.example.soporte.core.security.TokenManager
import com.example.soporte.features.auth.data.remote.AuthApi
import com.example.soporte.features.auth.data.remote.dto.LoginRequestDto
import com.example.soporte.features.auth.domain.model.User
import com.example.soporte.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun login(rut: String): NetworkResult<User> {
        return try {
            val response = api.login(LoginRequestDto(rut = rut))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                tokenManager.saveToken(body.token)

                NetworkResult.Success(
                    User(
                        rut = body.user.rut,
                        nombre = body.user.nombre,
                        correo = body.user.correo
                    )
                )
            } else {
                NetworkResult.Error("Error Login: ${response.code()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error("Excepción: ${e.message}", e)
        }
    }

    override fun logout() {
        tokenManager.clearToken()
    }

    override fun isUserLoggedIn(): Boolean {
        return !tokenManager.getToken().isNullOrBlank()
    }
}
