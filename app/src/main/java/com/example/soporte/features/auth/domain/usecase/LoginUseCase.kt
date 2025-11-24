package com.example.soporte.features.auth.domain.usecase

import com.example.soporte.core.network.NetworkResult
import com.example.soporte.features.auth.data.repository.OAuthRepository
import com.example.soporte.features.auth.domain.model.User
import com.example.soporte.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val oauthRepository: OAuthRepository
){
    suspend operator fun invoke(rut: String): NetworkResult<User> {
        if (rut.isBlank()) {
            return NetworkResult.Error("El RUT no puede estar vacío")
        }
        if (!oauthRepository.hasValidToken()) {
            val success = oauthRepository.fetchAndSaveToken()
            if (!success) {
                return NetworkResult.Error("Error de conexión con el servidor (OAuth)")
            }
        }
        return authRepository.login(rut)
    }
}
