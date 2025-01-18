package com.example.assignment_svg

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class DogImageResponse(val message: String)

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogImageResponse

    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        fun create(): DogApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(DogApiService::class.java)
        }
    }
}
