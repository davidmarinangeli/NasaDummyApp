package com.example.nasabucket.network

import com.squareup.moshi.Json

data class NasaAPOD(

    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)


