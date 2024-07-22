// File path: app/src/main/java/com/example/cars/repository/OAuthRepository.kt

package com.example.cars.repository

import com.example.cars.network.OAuthService

class OAuthRepository(private val service: OAuthService) {
    suspend fun getOAuthCredentials(): String? {
        val response = service.getOAuthCredentials()
        return if (response.isSuccessful) response.body() else null
    }
}