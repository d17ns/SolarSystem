package com.d17ns.solarsystem.network

import com.squareup.moshi.Json

/*
 * data class untuk mendefinisikan variabel name, image, description
 * dan digunakan oleh Moshi untuk menyocokkan dengan nama value dari file JSON
 */
data class Planet(
    val name: String,
    @Json(name = "image") val imageSrc: String,
    val description: String
)