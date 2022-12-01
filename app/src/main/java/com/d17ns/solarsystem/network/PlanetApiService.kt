package com.d17ns.solarsystem.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// variabel BASE_URL yang mendefinisikan master URL dari file JSON yang akan digunakan
private const val BASE_URL = "https://raw.githubusercontent.com/Lazzaro83/Solar-System/master/"

// build Moshi object dengan kotlin adapter factory yang akan digunakan oleh Retrofit
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

// Retrofit object dengan Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// interface PlanetApiService yang memanggil file JSON dari master URL dengan method GET
interface PlanetApiService {
    @GET("planets.json")
    suspend fun getPlanets(): List<Planet>
}

// object PlanetApi sebagai Api object
object PlanetApi {
    val retrofitService: PlanetApiService by lazy {
        retrofit.create(PlanetApiService::class.java)
    }
}