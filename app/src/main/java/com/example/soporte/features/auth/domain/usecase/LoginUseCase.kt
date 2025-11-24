package com.example.soporte.features.auth.domain.usecase

import com.example.soporte.core.network.NetworkResult
import com.example.soporte.features.auth.domain.model.User
import com.example.soporte.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(rut: String): NetworkResult<User> {
        if (rut.isBlank()) {
            return NetworkResult.Error("El RUT no puede estar vacío")
        }
        return repository.login(rut)
    }
}
