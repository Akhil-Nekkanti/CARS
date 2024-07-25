// File path: app/src/main/java/com/example/cars/util/NetworkModule.kt

package com.example.cars.util

import com.example.cars.network.OAuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://us-central1-cars-92596.cloudfunctions.net/function-1" // Replace REGION and PROJECT_ID with your actual values

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val oAuthService: OAuthService by lazy {
        retrofit.create(OAuthService::class.java)
    }
}