# soporte (com.example.soporte)

Base Clean Architecture con Hilt, Retrofit, Room y Compose. Estructura feature-first para aislar cada módulo de negocio y compartir núcleo de red/seguridad/BD.

## Estructura

```
com/example/soporte
├── SoporteApp.kt              # Application @HiltAndroidApp + WorkManager factory
├── MainActivity.kt            # Entry point (Compose)
├── core/                      # Núcleo compartido
│   ├── di/                    # Módulos Hilt
│   │   ├── NetworkModule.kt   # Retrofit/OkHttp + Apis (login, tickets, oauth)
│   │   ├── DatabaseModule.kt  # Room (AppDatabase, TicketDao)
│   │   ├── AuthModule.kt      # Bind AuthRepository
│   │   └── TicketsModule.kt   # Bind TicketRepository
│   ├── network/               # HTTP helpers
│   │   ├── AuthInterceptor.kt # Inyecta Authorization Bearer token maestro
│   │   └── NetworkResult.kt   # Wrapper Success/Error/Loading
│   ├── database/              # Room
│   │   ├── AppDatabase.kt
│   │   └── TypeConverters.kt
│   └── security/              # Almacenamiento seguro de token
│       └── TokenManager.kt    # EncryptedSharedPreferences + MasterKey
└── features/                  # Módulos de negocio
    ├── auth/
    │   ├── data/remote/       # AuthApi, OAuthApi, DTOs
    │   ├── data/repository/   # AuthRepositoryImpl, OAuthRepository
    │   ├── domain/model/      # User
    │   ├── domain/repository/ # AuthRepository (contrato)
    │   ├── domain/usecase/    # LoginUseCase, CheckAuthStatusUseCase
    │   └── presentation/      # LoginViewModel, SplashViewModel (UI pendiente)
    ├── tickets/
    │   ├── data/local/        # TicketDao, TicketEntity
    │   ├── data/remote/       # TicketApi, DTOs
    │   ├── data/repository/   # TicketRepositoryImpl (offline-first)
    │   ├── domain/model/      # Ticket
    │   ├── domain/repository/ # TicketRepository
    │   └── domain/usecase/    # (por agregar: GetTicketsUseCase, etc.)
    └── profile/               # Placeholder para perfil/config (pendiente)
```

## Flujo de autenticación (Client Credentials)
- `OAuthRepository` pide token maestro a `/token` con `BuildConfig.OAUTH_CLIENT_ID/SECRET` y lo guarda en `TokenManager`.
- `AuthInterceptor` adjunta `Authorization: Bearer <token maestro>` a todas las peticiones excepto `/token`.
- `LoginUseCase` garantiza token maestro (lo pide si falta) y luego llama a `AuthRepository.login(rut)`; el token retornado por login es solo informativo para UI.

## Configuración de compilación
- Credenciales OAuth en `app/build.gradle.kts` via `buildConfigField`.
- Compose activo, desugaring para java.time, KSP para Hilt/Room/Moshi.
- `NetworkModule` usa tres base URLs:
  - Login: `https://api.hcsba.cl/mobile/1/`
  - Tickets: `https://api.hcsba.cl/soporte/v1/`
  - OAuth: `https://api.hcsba.cl/`

## Próximos pasos sugeridos
- Implementar UI Compose para login/splash consumiendo `LoginViewModel` y `SplashViewModel`.
- Añadir casos de uso de tickets (GetTicketsUseCase, Sync, Assign, etc.) y bindings en Hilt si son necesarios.
- Crear módulos de perfil (API, repositorio, casos de uso) y sus pantallas.
- Añadir workers de sincronización offline en `tickets/data/worker` y registrarlos. 
