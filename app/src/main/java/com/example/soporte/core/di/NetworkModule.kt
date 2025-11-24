package com.example.soporte.core.di

import com.example.soporte.core.network.AuthInterceptor
import com.example.soporte.features.auth.data.remote.AuthApi
import com.example.soporte.features.auth.data.remote.OAuthApi
import com.example.soporte.features.tickets.data.remote.TicketApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.inject.Named
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL_LOGIN = "https://api.hcsba.cl/mobile/1/"
    private const val BASE_URL_TICKETS = "https://api.hcsba.cl/soporte/v1/"
    private const val BASE_URL_OAUTH = "https://api.hcsba.cl/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val timeout = 30.seconds.toJavaDuration()
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(authInterceptor)
            .connectTimeout(timeout)
            .readTimeout(timeout)
            .build()
    }

    @Provides
    @Singleton
    @Named("retrofit_login")
    fun provideRetrofitLogin(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_LOGIN)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    @Named("retrofit_tickets")
    fun provideRetrofitTickets(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_TICKETS)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    @Named("retrofit_oauth")
    fun provideRetrofitOauth(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_OAUTH)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideAuthApi(@Named("retrofit_login") retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideTicketApi(@Named("retrofit_tickets") retrofit: Retrofit): TicketApi =
        retrofit.create(TicketApi::class.java)

    @Provides
    @Singleton
    fun provideOAuthApi(@Named("retrofit_oauth") retrofit: Retrofit): OAuthApi =
        retrofit.create(OAuthApi::class.java)
}
