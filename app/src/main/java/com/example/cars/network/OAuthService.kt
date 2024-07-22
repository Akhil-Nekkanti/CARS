// File path: app/src/main/java/com/example/cars/network/OAuthService.kt

package com.example.cars.network

import retrofit2.Response
import retrofit2.http.GET

interface OAuthService {
    @GET("getOAuthCredentials") // Replace with the actual path to your Cloud Function
    suspend fun getOAuthCredentials(): Response<String>
}