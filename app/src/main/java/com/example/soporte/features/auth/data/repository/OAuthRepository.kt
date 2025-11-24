package com.example.soporte.features.auth.data.repository

import com.example.soporte.BuildConfig
import com.example.soporte.core.security.TokenManager
import com.example.soporte.features.auth.data.remote.OAuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthRepository @Inject constructor(
    private val api: OAuthApi,
    private val tokenManager: TokenManager
) {
    suspend fun fetchAndSaveToken(): Boolean {
        return try {
            val response = api.getAccessToken(
                clientId = BuildConfig.OAUTH_CLIENT_ID,
                clientSecret = BuildConfig.OAUTH_CLIENT_SECRET
            )
            if (response.isSuccessful) {
                response.body()?.accessToken?.let { token ->
                    tokenManager.saveToken(token)
                    return true
                }
            }
            false
        } catch (e: Exception) {
            false
        }
    }

    fun hasValidToken(): Boolean = !tokenManager.getToken().isNullOrBlank()
}
