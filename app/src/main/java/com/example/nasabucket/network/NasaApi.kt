package com.example.nasabucket.network

import com.example.nasabucket.data.NasaAPOD
import com.example.nasabucket.data.NasaMarsRover
import com.example.nasabucket.data.Photos
import com.example.nasabucket.data.Rover
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val APOD_ENDPOINT_KEY = "planetary/"
private const val ROVER_ENDPOINT_KEY = "mars-photos/api/v1/rovers/curiosity/photos/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface NasaApiService {

    @GET("${APOD_ENDPOINT_KEY}apod?api_key=$API_KEY")
    fun getAPODAsync(): Deferred<NasaAPOD>

    @GET(ROVER_ENDPOINT_KEY)
    fun getMarsRoverPhotosAsync(
        @Query("sol") sol: Number,
        @Query("camera") camera: String,
        @Query("api_key") api_key: String = API_KEY
    ): Deferred<NasaMarsRover>

}

object NasaApi {
    val retrofitService: NasaApiService by lazy {
        retrofit.create(NasaApiService::class.java)
    }

}