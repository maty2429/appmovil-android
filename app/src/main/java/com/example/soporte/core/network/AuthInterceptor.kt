package com.example.soporte.core.network

import com.example.soporte.core.security.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = tokenManager.getToken()

        if (token.isNullOrBlank()) {
            return chain.proceed(request)
        }

        val authenticatedRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(authenticatedRequest)
    }
}
